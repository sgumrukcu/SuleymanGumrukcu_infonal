package com.suleyman.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.suleyman.dao.Dao;
import com.suleyman.model.User;

public class UserControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Dao dao;

	public UserControllerServlet() {
		dao = new Dao();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		List<User> userList = new ArrayList<User>();
		Gson gson = new Gson();
		response.setContentType("application/json");

		if (action != null) {
			if (action.equals("list")) {
				try {
					userList = dao.getAllUsers();
					JsonElement element = gson.toJsonTree(userList,
							new TypeToken<List<User>>() {
							}.getType());
					JsonArray jsonArray = element.getAsJsonArray();
					String listData = jsonArray.toString();

					listData = "{\"Result\":\"OK\",\"Records\":" + listData
							+ "}";
					response.getWriter().print(listData);
				} catch (Exception e) {
					String error = "{\"Result\":\"ERROR\",\"Message\":"
							+ "kayıtlar listelenirken hata oluştu! }";
					response.getWriter().print(error);
					System.err.println(e.getMessage());
				}
			} else if (action.equals("create") || action.equals("update")) {
				User user = new User();
				try {
					int userId = Integer.parseInt(request
							.getParameter("userId"));
					user.setUserId(userId);

					String name = request.getParameter("name");
					user.setName(name);

					String surname = request.getParameter("surname");
					user.setSurname(surname);

					String phoneNumber = request.getParameter("phoneNumber");
					user.setPhoneNumber(phoneNumber);

					if (action.equals("create")) {
						dao.addUser(user);
						userList.add(user);
						String json = gson.toJson(user);
						String listData = "{\"Result\":\"OK\",\"Record\":"
								+ json + "}";
						response.getWriter().print(listData);
					} else if (action.equals("update")) {
						dao.updateUser(user);
						String json = gson.toJson(user);
						String listData = "{\"Result\":\"OK\",\"Record\":"
								+ json + "}";
						response.getWriter().print(listData);
					}
				} catch (Exception e) {
					String error = "{\"Result\":\"ERROR\",\"Message\":"
							+ "kayıt yaratılırken hata oluştu! }";
					response.getWriter().print(error);
					System.err.println(e.getMessage());
				}

			} else if (action.equals("delete")) {
				try {
					if (request.getParameter("userId") != null) {
						int userId = Integer.parseInt(request
								.getParameter("userId"));
						dao.deleteUser(userId);
						String listData = "{\"Result\":\"OK\"}";
						response.getWriter().print(listData);
					}
				} catch (Exception e) {
					String error = "{\"Result\":\"ERROR\",\"Message\":"
							+ "kayıtlar silinirken hata oluştu! }";
					response.getWriter().print(error);
					System.err.println(e.getMessage());
				}
			}
		}
	}
}