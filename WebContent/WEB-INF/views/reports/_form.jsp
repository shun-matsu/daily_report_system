<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br/>
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}"/><br/>
        </c:forEach>
    </div>
</c:if>
<label for="report_date">日付</label><br/>
<input type="date" name="report_date" value="<fmt:formatDate value='${report.report_date}' pattern='yyyy-MM-dd'/>"/>
<br/><br/>

<label for="arrived_at">出勤時間</label><br/>
<input type="time" name="arrived_at" value="<fmt:formatDate value='${report.arrived_at}' pattern='HH:mm'/>"/>
<br/><br/>

<label for="leaved_at">退勤時間</label><br/>
<input type="time" name="leaved_at" value="<fmt:formatDate value='${report.leaved_at}' pattern='HH:mm'/>"/>
<br/><br/>

<label for="name">氏名</label><br/>
<c:out value="${sessionScope.login_employee.name}"/>
<br/><br/>

<label for="title">タイトル</label><br/>
<input type="text" name="title" value="${report.title}"/>
<br/><br/>

<label for="content">内容</label><br/>
<textarea name="content" rows="10" cols="50">${report.content}</textarea>
<br/><br/>

<label for="company_id">取引先</label><br/>
<select name="company_id">
    <option value="">選択してください</option>
    <c:forEach var="company" items="${companies}">
        <option value="${company.id}"><c:out value="${company.code}"/>:<c:out value="${company.name}"/></option>
    </c:forEach>
</select>
<br/><br/>

<label for="business_mtg">商談状況</label><br/>
<textarea name="business_mtg" rows="10" cols="50">${report.business_mtg}</textarea>
<br/><br/>

<input type="hidden" name="_token" value="${_token}"/>
<button type="submit">投稿</button>