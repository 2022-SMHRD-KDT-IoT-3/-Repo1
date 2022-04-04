<%@page import="Model.device_infoDAO"%>
<%@page import="org.json.simple.JSONArray"%>

<%@ page language="java" contentType="application/json; charset=UTF-8"

pageEncoding="UTF-8"%>

<%

device_infoDAO Device_infoDAO = device_infoDAO.getInstance();

JSONArray jsonArray = Device_infoDAO.getDevice();

%>

<%= jsonArray %>
