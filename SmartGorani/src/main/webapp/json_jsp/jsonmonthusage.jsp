
<%@page import="Model.ElectricDAO"%>
<%@page import="org.json.simple.JSONArray"%>

<%@ page language="java" contentType="application/json; charset=UTF-8"

pageEncoding="UTF-8"%>

<%

ElectricDAO electricDAO = ElectricDAO.getInstance();

JSONArray jsonArray = electricDAO.monthUsage();

%>

<%= jsonArray %>
