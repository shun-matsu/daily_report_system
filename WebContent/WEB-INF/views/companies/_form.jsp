<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br/>
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}"/><br/>
        </c:forEach>
    </div>
</c:if>
<label for="code">取引先コード</label><br/>
<input type="text" name="code" value="${company.code}"/>
<br/><br/>

<label for="name">会社名</label><br/>
<input type="text" name="name" value="${company.name}"/>
<br/><br/>

<label for="content">業務内容</label><br/>
<textarea name="content" rows="10" cols="50">${company.content}</textarea>
<br/><br/>

<input type="hidden" name="_token" value="${_token }"/>
<button type="submit">投稿</button>