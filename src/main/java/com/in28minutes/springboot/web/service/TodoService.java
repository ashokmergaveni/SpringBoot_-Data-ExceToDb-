package com.in28minutes.springboot.web.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.in28minutes.springboot.web.controller.CamdenDb;
import com.in28minutes.springboot.web.controller.User;
import com.in28minutes.springboot.web.model.Todo;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<Todo>();
    
    private static CamdenDb camdenDb=new CamdenDb();
    private static int todoCount = 3;

    public List<User> retrieveTodos(String user) throws ClassNotFoundException, SQLException {
    	
    	List<User> data=camdenDb.getUserDetails();
    	
        /*List<Todo> filteredTodos = new ArrayList<Todo>();
        for (Todo todo : todos) {
         //   if (todo.getUser().equalsIgnoreCase(user)) {
                filteredTodos.add(todo);
           // }
        }*/
        return data;
    }
    
    public Todo retrieveTodo(int id) {
        for (Todo todo : todos) {
            if (todo.getId()==id) {
                return todo;
            }
        }
        return null;
    }

    public void updateTodo(String[] eventId) throws ClassNotFoundException, SQLException{
    	try {
    CamdenDb camdenDb=new CamdenDb();
    camdenDb.updateEventState(eventId);
    	}catch(Exception e){
    		System.out.println("db exceptiion");
    	}
    	
    }

   /* public void addTodo(String name, String desc, Date targetDate,
            boolean isDone) {
        todos.add(new Todo(++todoCount, name, desc, targetDate, isDone));
    }*/

    public void deleteTodo(int id) {
        Iterator<Todo> iterator = todos.iterator();
        while (iterator.hasNext()) {
            Todo todo = iterator.next();
            if (todo.getId() == id) {
                iterator.remove();
            }
        }
    }

	public void deleteEvents(String[] eventId) {
		// TODO Auto-generated method stub
		try {
		    CamdenDb camdenDb=new CamdenDb();
		    camdenDb.deleteEvnets(eventId);
		    	}catch(Exception e){
		    		System.out.println("db exceptiion");
		    	}
		    	
		    }
}