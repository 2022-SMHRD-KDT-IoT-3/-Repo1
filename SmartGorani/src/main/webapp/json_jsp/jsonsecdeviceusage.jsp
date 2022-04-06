
<%@page import="Model.DeviceDAO"%>
<%@page import="org.json.simple.JSONArray"%>

<%@ page language="java" contentType="application/json; charset=UTF-8"

pageEncoding="UTF-8"%>

<%

DeviceDAO deviceDAO = DeviceDAO.getInstance();

JSONArray jsonArray = deviceDAO.devicesecUsage();

%>

<%= jsonArray %>
