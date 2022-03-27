<%@page import="arduino.MemberDao"%>
<%@page import="arduino.chart"%>
<%@page import="Model.chartTest"%>
<%@page import="org.json.simple.JSONArray"%>

<%@ page language="java" contentType="application/json; charset=UTF-8"

pageEncoding="UTF-8"%>

<%

MemberDao memberDao = MemberDao.getInstance();

JSONArray jsonArray = memberDao.getCountAddress();

%>

<%= jsonArray %>
