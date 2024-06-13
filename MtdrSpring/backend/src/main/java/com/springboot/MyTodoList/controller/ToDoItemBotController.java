package com.springboot.MyTodoList.controller;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;
import java.io.PrintStream;





import com.springboot.MyTodoList.model.Tarea;
import com.springboot.MyTodoList.service.TareaService;
import com.springboot.MyTodoList.model.Usuario;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.springboot.MyTodoList.model.ToDoItem;
import com.springboot.MyTodoList.service.ToDoItemService;
import com.springboot.MyTodoList.service.UsuarioService;
import com.springboot.MyTodoList.util.BotCommands;
import com.springboot.MyTodoList.util.BotHelper;
import com.springboot.MyTodoList.util.BotLabels;
import com.springboot.MyTodoList.util.BotMessages;

public class ToDoItemBotController extends TelegramLongPollingBot {

	private static final Logger logger = LoggerFactory.getLogger(ToDoItemBotController.class);
	private ToDoItemService toDoItemService;
	private TareaService tareaService;
	private UsuarioService usuarioService;
	private String botName;

	private Map<Long, String> context = new HashMap<>();
	String nombreTarea = "";

	private boolean initialMessageSent = false;


	@Autowired
	public ToDoItemBotController(String botToken, String botName, ToDoItemService toDoItemService, TareaService tareaService ,UsuarioService usuarioService) {
		super(botToken);
		logger.info("Bot Token: " + botToken);
		logger.info("Bot name: " + botName);
		this.toDoItemService = toDoItemService;
		this.tareaService = tareaService;
		this.usuarioService = usuarioService;
		this.botName = botName;


		
	}
	// waitForUserResponse() debería capturar y devolver el texto del mensaje del usuario
		private String waitForUserResponse(Update update) {
			String message = update.getMessage().getText();
			if (message != null) {
				return message;
			}
			return null;  // Maneja casos donde no se pudo capturar la respuesta del usuario
		}

	@Override
	public void onUpdateReceived(Update update) {

		
    if (update.hasMessage() && update.getMessage().hasText()) {

		String messageTextFromTelegram = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();
        Long user_id = update.getMessage().getChat().getId();

        // Verificar si el usuario ya existe
        ResponseEntity<Usuario> userResponse = usuarioService.getItemByTelegramUsername(user_id);
        Usuario usuario;
        if (userResponse.getStatusCode() == HttpStatus.OK) {
            usuario = userResponse.getBody();
        } else {
            // El usuario no existe, proceder con la creación
            if (!context.containsKey(user_id) && !initialMessageSent) {
                // Primer mensaje para pedir nombre
                SendMessage messageToTelegram = new SendMessage();
                messageToTelegram.setChatId(chatId);
                messageToTelegram.setText("Hola! Parece que eres un usuario nuevo. ¿Cuál es tu nombre?");

                try {
                    execute(messageToTelegram);
                } catch (TelegramApiException e) {
                    logger.error("Error al enviar mensaje: " + e.getMessage());
                }

                // Guardar estado actual para este usuario
                context.put(user_id, "waiting_for_name");
				initialMessageSent = true;
                return;
            }

            // Si ya se pidió el nombre, esperar apellido
            if (context.get(user_id).equals("waiting_for_name")) {
                // Guardar nombre y preguntar por el apellido
                String nombre = messageTextFromTelegram.trim();
                context.put(user_id+1, nombre);

                SendMessage messageToTelegram = new SendMessage();
                messageToTelegram.setChatId(chatId);
                messageToTelegram.setText("¿Cuál es tu apellido?");

                try {
                    execute(messageToTelegram);
                } catch (TelegramApiException e) {
                    logger.error("Error al enviar mensaje: " + e.getMessage());
                }

                // Actualizar estado para esperar el apellido
                context.put(user_id, "waiting_for_lastname");
                return;
            }

            // Si ya se pidió el apellido, guardar y continuar
            if (context.get(user_id).equals("waiting_for_lastname")) {
                String apellido = messageTextFromTelegram.trim();
                usuario = new Usuario();
                usuario.setTelegramUsername(user_id);
                usuario.setNombre1(context.get(user_id+1));
                usuario.setApellido1(apellido);

                // Agregar usuario a la base de datos
                usuarioService.addUsuario(usuario);

                SendMessage messageToTelegram = new SendMessage();
                messageToTelegram.setChatId(chatId);

                String name = context.get(user_id+1);
                messageToTelegram.setText("Bienvenido " + name + " " + apellido + " !!");


                try {
                    execute(messageToTelegram);
                } catch (TelegramApiException e) {
                    logger.error("Error al enviar mensaje: " + e.getMessage());
                }

                // Limpiar contexto para este usuario
                context.remove(user_id);
				context.remove(user_id + 1);
            }
        }

			
			// ---------------------------------------------------- MAIN SCREEN ----------------------------------------------------------
			// ---------------------------------------------------- MAIN SCREEN ----------------------------------------------------------
			// ---------------------------------------------------- MAIN SCREEN ----------------------------------------------------------
			if (messageTextFromTelegram.equals(BotCommands.START_COMMAND.getCommand())
					|| messageTextFromTelegram.equals(BotLabels.SHOW_MAIN_SCREEN.getLabel())) {

				SendMessage messageToTelegram = new SendMessage();
				messageToTelegram.setChatId(chatId);
				messageToTelegram.setText(BotMessages.HELLO_MYTODO_BOT.getMessage());

				ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
				List<KeyboardRow> keyboard = new ArrayList<>();

				// first row
				KeyboardRow row = new KeyboardRow();
				row.add(BotLabels.ADD_NEW_ITEM.getLabel());
				row.add(BotLabels.MY_TASKS.getLabel());
				row.add(BotLabels.COMPLETED_TASKS.getLabel());
				row.add(BotLabels.ALL_TASKS_MANAGER.getLabel());
				// Add the first row to the keyboard
				keyboard.add(row);

				// second row
				row = new KeyboardRow();
				row.add(BotLabels.HIDE_MAIN_SCREEN.getLabel());
				keyboard.add(row);

				// Set the keyboard
				keyboardMarkup.setKeyboard(keyboard);

				// Add the keyboard markup
				messageToTelegram.setReplyMarkup(keyboardMarkup);

				try {
					execute(messageToTelegram);
				} catch (TelegramApiException e) {
					logger.error(e.getLocalizedMessage(), e);
				}
			
			// ---------------------------------------------------- DONE ----------------------------------------------------------
			// ---------------------------------------------------- DONE ----------------------------------------------------------
			// ---------------------------------------------------- DONE ----------------------------------------------------------	
			} else if (messageTextFromTelegram.indexOf(BotLabels.DONE.getLabel()) != -1) {

				String done = messageTextFromTelegram.substring(0,
						messageTextFromTelegram.indexOf(BotLabels.DASH.getLabel()));
				Integer id = Integer.valueOf(done);

				try {
					Tarea tarea = getTareaById(id);
					BotHelper.sendMessageToTelegram(chatId, BotMessages.ITEM_DONE.getMessage(), this);
					tarea.setCompletado(true);
					updateTarea(tarea, id);
					

				} catch (Exception e) {
					logger.error(e.getLocalizedMessage(), e);
				}

		
            
			
			// ---------------------------------------------------- UNDO ----------------------------------------------------------
			// ---------------------------------------------------- UNDO ----------------------------------------------------------
			// ---------------------------------------------------- UNDO ----------------------------------------------------------		
			} else if (messageTextFromTelegram.indexOf(BotLabels.UNDO.getLabel()) != -1) {

				String undo = messageTextFromTelegram.substring(0,
						messageTextFromTelegram.indexOf(BotLabels.DASH.getLabel()));
				Integer id = Integer.valueOf(undo);

				try {

					ToDoItem item = getToDoItemById(id).getBody();
					item.setDone(false);
					updateToDoItem(item, id);
					BotHelper.sendMessageToTelegram(chatId, BotMessages.ITEM_UNDONE.getMessage(), this);

				} catch (Exception e) {
					logger.error(e.getLocalizedMessage(), e);
				}
			
			// ---------------------------------------------------- DELETE ----------------------------------------------------------
			// ---------------------------------------------------- DELETE ----------------------------------------------------------
			// ---------------------------------------------------- DELETE ----------------------------------------------------------		
			} else if (messageTextFromTelegram.indexOf(BotLabels.DELETE.getLabel()) != -1) {

				String delete = messageTextFromTelegram.substring(0,
						messageTextFromTelegram.indexOf(BotLabels.DASH.getLabel()));
				Integer id = Integer.valueOf(delete);

				try {

					deleteToDoItem(id).getBody();
					BotHelper.sendMessageToTelegram(chatId, BotMessages.ITEM_DELETED.getMessage(), this);

				} catch (Exception e) {
					logger.error(e.getLocalizedMessage(), e);
				}
			
			// ---------------------------------------------------- HIDE MAIN SCREEN ----------------------------------------------------------
			// ---------------------------------------------------- HIDE MAIN SCREEN ----------------------------------------------------------
			// ---------------------------------------------------- HIDE MAIN SCREEN ----------------------------------------------------------	
			} else if (messageTextFromTelegram.equals(BotCommands.HIDE_COMMAND.getCommand())
					|| messageTextFromTelegram.equals(BotLabels.HIDE_MAIN_SCREEN.getLabel())) {

				BotHelper.sendMessageToTelegram(chatId, BotMessages.BYE.getMessage(), this);

					
			}
			// MY TASKS
			else if (messageTextFromTelegram.equals(BotCommands.MY_TASKS.getCommand()) || messageTextFromTelegram.equals(BotLabels.MY_TASKS.getLabel())) {
                ResponseEntity<List<Tarea>> response = getAllTareasByidUsuario(user_id);

                SendMessage messageToTelegram = new SendMessage();
                messageToTelegram.setChatId(chatId);

                if (response.getStatusCode() == HttpStatus.OK) {
                    List<Tarea> tareas = response.getBody();
                    StringBuilder messageBuilder = new StringBuilder("Tareas del usuario:\n");
                    for (Tarea tarea : tareas) {
                        messageBuilder.append(tarea.getNombre()).append("\n");
                    }
                    messageToTelegram.setText(messageBuilder.toString());

                    ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
                    List<KeyboardRow> keyboard = new ArrayList<>();

                    // Command back to main screen
                    KeyboardRow mainScreenRowTop = new KeyboardRow();
                    mainScreenRowTop.add(BotLabels.SHOW_MAIN_SCREEN.getLabel());
                    keyboard.add(mainScreenRowTop);

                    KeyboardRow firstRow = new KeyboardRow();
                    firstRow.add(BotLabels.ADD_NEW_ITEM.getLabel());
                    keyboard.add(firstRow);

                    List<Tarea> activeItems = tareas.stream().filter(item -> !item.isCompletado()).collect(Collectors.toList());

                    for (Tarea tarea : activeItems) {
                        if (tarea.getDescripcion() != null) { // Check for null descriptions
                            KeyboardRow currentRow = new KeyboardRow();
                            currentRow.add(tarea.getDescripcion());
                            currentRow.add(tarea.getId() + BotLabels.DASH.getLabel() + BotLabels.DONE.getLabel());
                            keyboard.add(currentRow);
                        }
						else{
							KeyboardRow currentRow = new KeyboardRow();
                            currentRow.add(tarea.getNombre());
                            currentRow.add(tarea.getId() + BotLabels.DASH.getLabel() + BotLabels.DONE.getLabel());
                            keyboard.add(currentRow);

						}
                    }

                    List<Tarea> doneTareas = tareas.stream().filter(Tarea::isCompletado).collect(Collectors.toList());

                    for (Tarea tarea : doneTareas) {
                        if (tarea.getDescripcion() != null) { // Check for null descriptions
                            KeyboardRow currentRow = new KeyboardRow();
                            currentRow.add(tarea.getDescripcion());
                            currentRow.add(tarea.getId() + BotLabels.DASH.getLabel() + BotLabels.UNDO.getLabel());
                            currentRow.add(tarea.getId() + BotLabels.DASH.getLabel() + BotLabels.DELETE.getLabel());
                            keyboard.add(currentRow);
                        }
                    }

                    keyboardMarkup.setKeyboard(keyboard);
                    messageToTelegram.setReplyMarkup(keyboardMarkup);

                } else {
                    messageToTelegram.setText("No se encontraron tareas para tu usuario.");
                }

                try {
                    execute(messageToTelegram);
                } catch (TelegramApiException e) {
                    logger.error("Error al enviar mensaje: " + e.getMessage());
                }
            }


			// M Y    T A S K S COMPLETED

			else if (messageTextFromTelegram.equals(BotCommands.COMPLETED_TASKS.getCommand()) || messageTextFromTelegram.equals(BotLabels.COMPLETED_TASKS.getLabel())) {
                ResponseEntity<List<Tarea>> response = getAllTareasByidUsuarioCompleted(user_id);

                SendMessage messageToTelegram = new SendMessage();
                messageToTelegram.setChatId(chatId);

                if (response.getStatusCode() == HttpStatus.OK) {
                    List<Tarea> tareasCompletadas = response.getBody();
                    StringBuilder messageBuilder = new StringBuilder("Tareas completadas del usuario:\n");
                    for (Tarea tareaCompletada : tareasCompletadas) {
                        messageBuilder.append(tareaCompletada.getNombre()).append("\n");
                    }
                    messageToTelegram.setText(messageBuilder.toString());

                    ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
                    List<KeyboardRow> keyboard = new ArrayList<>();

                    // Command back to main screen
                    KeyboardRow mainScreenRowTop = new KeyboardRow();
                    mainScreenRowTop.add(BotLabels.SHOW_MAIN_SCREEN.getLabel());
                    keyboard.add(mainScreenRowTop);

                    KeyboardRow firstRow = new KeyboardRow();
                    firstRow.add(BotLabels.ADD_NEW_ITEM.getLabel());
                    keyboard.add(firstRow);

                    for (Tarea tareaCompletada : tareasCompletadas) {
                        if (tareaCompletada.getDescripcion() != null) { // Check for null descriptions
                            KeyboardRow currentRow = new KeyboardRow();
                            currentRow.add(tareaCompletada.getDescripcion());
                            currentRow.add(tareaCompletada.getId() + BotLabels.DASH.getLabel() + BotLabels.UNDO.getLabel());
                            currentRow.add(tareaCompletada.getId() + BotLabels.DASH.getLabel() + BotLabels.DELETE.getLabel());
                            keyboard.add(currentRow);
                        }
						else{
							KeyboardRow currentRow = new KeyboardRow();
                            currentRow.add(tareaCompletada.getNombre());
                            currentRow.add(tareaCompletada.getId() + BotLabels.DASH.getLabel() + BotLabels.DONE.getLabel());
                            keyboard.add(currentRow);

						}
                    }

                    keyboardMarkup.setKeyboard(keyboard);
                    messageToTelegram.setReplyMarkup(keyboardMarkup);

                } else {
                    messageToTelegram.setText("No se encontraron tareas completadas.");
                }

                try {
                    execute(messageToTelegram);
                } catch (TelegramApiException e) {
                    logger.error("Error al enviar mensaje: " + e.getMessage());
                }
            }


			// M Y    T A S K S COMPLETED

			// ------------------------ MANAGER 
			else if (messageTextFromTelegram.equals(BotCommands.ALL_TASKS_MANAGER.getCommand())
					|| messageTextFromTelegram.equals(BotLabels.ALL_TASKS_MANAGER.getLabel())) {
						// Verificar si el usuario ya existe
						ResponseEntity<Usuario> userResponse2 = usuarioService.getItemByTelegramUsername(user_id);
						Usuario usuario2;
						if (userResponse2.getStatusCode() == HttpStatus.OK) {
							usuario2 = userResponse2.getBody();
							long userId = usuario2.getId();
							boolean isManager = usuarioService.isManagerById(userId);
						
							if (!isManager) {
								BotHelper.sendMessageToTelegram(chatId, "No tienes permisos para ver todas las tareas del equipo.", this);
								return;
							}
							else{
								List<Tarea> tareas = tareaService.findTareasByManagerId(user_id);
								if (tareas.isEmpty()) {
									try {
										SendMessage messageToTelegram = new SendMessage();
										messageToTelegram.setChatId(chatId);
										messageToTelegram.setText("No hay tareas asignadas a tu equipo.");
										execute(messageToTelegram);
									} catch (TelegramApiException e) {
										logger.error("Error al enviar mensaje: " + e.getMessage());
									}
									
								} else {

									StringBuilder messageBuilder = new StringBuilder("Tareas del usuario:\n");
									for (Tarea tarea : tareas) {
										messageBuilder.append(tarea.getNombre()).append("\n");
									}
									SendMessage messageToTelegram = new SendMessage();
									messageToTelegram.setChatId(chatId);
									messageToTelegram.setText(messageBuilder.toString());
									try {
										execute(messageToTelegram);
									} catch (TelegramApiException e) {
										logger.error("Error al enviar mensaje: " + e.getMessage());
									}

									ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
									List<KeyboardRow> keyboard = new ArrayList<>();
									keyboardMarkup = new ReplyKeyboardMarkup();
									keyboard = new ArrayList<>();

									// Command back to main screen
									KeyboardRow mainScreenRowTop = new KeyboardRow();
									mainScreenRowTop.add(BotLabels.SHOW_MAIN_SCREEN.getLabel());
									keyboard.add(mainScreenRowTop);

									KeyboardRow firstRow = new KeyboardRow();
									firstRow.add(BotLabels.ADD_NEW_ITEM.getLabel());
									keyboard.add(firstRow);
									for (Tarea tarea : tareas) {
										if (tarea.getDescripcion() != null) { // Check for null descriptions
											KeyboardRow currentRow = new KeyboardRow();
											currentRow.add(tarea.getDescripcion());
											currentRow.add(tarea.getId() + BotLabels.DASH.getLabel() + BotLabels.DONE.getLabel());
											keyboard.add(currentRow);
										}if((tarea.getNombre() != null) ){
											KeyboardRow currentRow = new KeyboardRow();
											currentRow.add(tarea.getNombre());
											currentRow.add(tarea.getId() + BotLabels.DASH.getLabel() + BotLabels.DONE.getLabel());
											keyboard.add(currentRow);

										}
									}
								}

							}

						} else {
							SendMessage messageToTelegram = new SendMessage();
							messageToTelegram.setChatId(chatId);
							messageToTelegram.setText("Error carnal");
							try {
								execute(messageToTelegram);
								} catch (TelegramApiException e) {
								logger.error("Error al enviar mensaje: " + e.getMessage());
							}
						}		
					}

			// ------------------------ MANAGER 
			
			// ---------------------------------------------------- TO-DO LIST ----------------------------------------------------------
			// ---------------------------------------------------- TO-DO LIST ----------------------------------------------------------
			// ---------------------------------------------------- TO-DO LIST ----------------------------------------------------------
			else if (messageTextFromTelegram.equals(BotCommands.TODO_LIST.getCommand())
					|| messageTextFromTelegram.equals(BotLabels.LIST_ALL_ITEMS.getLabel())
					|| messageTextFromTelegram.equals(BotLabels.MY_TODO_LIST.getLabel())) {

				List<ToDoItem> allItems = getAllToDoItems();
				ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
				List<KeyboardRow> keyboard = new ArrayList<>();

				// command back to main screen
				KeyboardRow mainScreenRowTop = new KeyboardRow();
				mainScreenRowTop.add(BotLabels.SHOW_MAIN_SCREEN.getLabel());
				keyboard.add(mainScreenRowTop);

				KeyboardRow firstRow = new KeyboardRow();
				firstRow.add(BotLabels.ADD_NEW_ITEM.getLabel());
				keyboard.add(firstRow);

				// Posiblemente añadir título "TO-DO LIST" para separar botones de arriba con tareas

				List<ToDoItem> activeItems = allItems.stream().filter(item -> item.isDone() == false)
						.collect(Collectors.toList());

				for (ToDoItem item : activeItems) {

					KeyboardRow currentRow = new KeyboardRow();
					currentRow.add(item.getDescription());
					currentRow.add(item.getID() + BotLabels.DASH.getLabel() + BotLabels.DONE.getLabel());
					keyboard.add(currentRow);
				}

				List<ToDoItem> doneItems = allItems.stream().filter(item -> item.isDone() == true)
						.collect(Collectors.toList());

				for (ToDoItem item : doneItems) {
					KeyboardRow currentRow = new KeyboardRow();
					currentRow.add(item.getDescription());
					currentRow.add(item.getID() + BotLabels.DASH.getLabel() + BotLabels.UNDO.getLabel());
					currentRow.add(item.getID() + BotLabels.DASH.getLabel() + BotLabels.DELETE.getLabel());
					keyboard.add(currentRow);
				}


				keyboardMarkup.setKeyboard(keyboard);

				SendMessage messageToTelegram = new SendMessage();
				messageToTelegram.setChatId(chatId);
				messageToTelegram.setText(BotLabels.MY_TODO_LIST.getLabel());
				messageToTelegram.setReplyMarkup(keyboardMarkup);

				try {
					execute(messageToTelegram);
				} catch (TelegramApiException e) {
					logger.error(e.getLocalizedMessage(), e);
				}

			// ---------------------------------------------------- ADD ITEM ----------------------------------------------------------
			// ---------------------------------------------------- ADD ITEM ----------------------------------------------------------
			// ---------------------------------------------------- ADD ITEM ----------------------------------------------------------	
			} 
			if (messageTextFromTelegram.equals(BotCommands.ADD_ITEM.getCommand()) || messageTextFromTelegram.equals(BotLabels.ADD_NEW_ITEM.getLabel())) {
				// Este bloque se ejecuta al recibir el comando para añadir una nueva tarea

				// Primer mensaje para pedir nombre de la tarea
				SendMessage messageToTelegram = new SendMessage();
				messageToTelegram.setChatId(chatId);
				messageToTelegram.setText("¿Cuál es el nombre de la tarea a crear?");

				try {
					execute(messageToTelegram);
				} catch (TelegramApiException e) {
					logger.error("Error al enviar mensaje: " + e.getMessage());
				}

				// Guardar estado actual para este usuario
				context.put(user_id, "waiting_for_task_name");
				return;
			}

			// Aquí deberías manejar la respuesta del usuario para capturar el nombre de la tarea
			String userState = context.get(user_id);
			if (userState != null && userState.equals("waiting_for_task_name")) {
				// Guardar nombre de la tarea
				String nombreTarea = messageTextFromTelegram.trim();

				// Crear la tarea
				Tarea tarea = new Tarea();
				tarea.setNombre(nombreTarea);
				tarea.setIdUsuario(user_id);
				tarea.setCompletado(false);

				// Agregar la tarea a la base de datos o servicio correspondiente
				tareaService.addTarea(tarea);

				// Enviar mensaje de confirmación al usuario
				SendMessage messageToTelegram = new SendMessage();
				messageToTelegram.setChatId(chatId);
				messageToTelegram.setText("Se ha creado la tarea " + nombreTarea + "!");

				try {
					execute(messageToTelegram);
				} catch (TelegramApiException e) {
					logger.error("Error al enviar mensaje: " + e.getMessage());
				}

				// Limpiar contexto para este usuario
				context.remove(user_id);
			}

			else {
				return;
			}
		}
	}

	@Override
	public String getBotUsername() {		
		return botName;
	}

	// GET /todolist
	public List<ToDoItem> getAllToDoItems() { 
		return toDoItemService.findAll();
	}

	// GET BY ID /todolist/{id}
	public ResponseEntity<ToDoItem> getToDoItemById(@PathVariable int id) {
		try {
			ResponseEntity<ToDoItem> responseEntity = toDoItemService.getItemById(id);
			return new ResponseEntity<ToDoItem>(responseEntity.getBody(), HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}



	
	

	// PUT /todolist
	public ResponseEntity addToDoItem(@RequestBody ToDoItem todoItem) throws Exception {
		ToDoItem td = toDoItemService.addToDoItem(todoItem);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("location", "" + td.getID());
		responseHeaders.set("Access-Control-Expose-Headers", "location");
		// URI location = URI.create(""+td.getID())

		return ResponseEntity.ok().headers(responseHeaders).build();
	}


	public ResponseEntity addTask(@RequestBody Tarea tarea) throws Exception {
		Tarea tarea1= tareaService.addTarea(tarea);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("location", "" + tarea1.getId());
		responseHeaders.set("Access-Control-Expose-Headers", "location");
		// URI location = URI.create(""+td.getID())

		return ResponseEntity.ok().headers(responseHeaders).build();
	}

	// UPDATE /todolist/{id}
	public ResponseEntity updateToDoItem(@RequestBody ToDoItem toDoItem, @PathVariable int id) {
		try {
			ToDoItem toDoItem1 = toDoItemService.updateToDoItem(id, toDoItem);
			System.out.println(toDoItem1.toString());
			return new ResponseEntity<>(toDoItem1, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}



	public ResponseEntity updateTarea(@RequestBody Tarea tarea, @PathVariable int id) {
		try {
			Tarea tarea1 = tareaService.updateTarea(id, tarea);
			System.out.println(tarea1.toString());
			return new ResponseEntity<>(tarea1, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	// DELETE todolist/{id}
	public ResponseEntity<Boolean> deleteToDoItem(@PathVariable("id") int id) {
		Boolean flag = false;
		try {
			flag = toDoItemService.deleteToDoItem(id);
			return new ResponseEntity<>(flag, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			return new ResponseEntity<>(flag, HttpStatus.NOT_FOUND);
		}
	}
	
    public ResponseEntity<List<Tarea>> getAllTareasByidUsuario(Long user_id) {
        List<Tarea> tareas = tareaService.findAllByidUsuario(user_id);
        if (tareas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tareas, HttpStatus.OK);
    }


	public ResponseEntity<Tarea> getTareaBynombre(String name) {
		try {
			Tarea tarea = tareaService.getTareaBynombre(name);
			return new ResponseEntity<>(tarea, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
    }

	public Tarea getTareaById(@PathVariable int id) {
        return tareaService.getTareaById(id);
    }

	public ResponseEntity<List<Tarea>> getAllTareasByidUsuarioCompleted(Long user_id) {
        List<Tarea> tareas = tareaService.findCompletedTasksByUserId(user_id);
        if (tareas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
     
	    return new ResponseEntity<>(tareas, HttpStatus.OK);
    }

}