<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8" %>
<html>
  <body>
    <%
        String pageframe=request.getParameter("pageframe");
        String func=request.getParameter("func");
     %>
      <div class="arrow_S margin">
        <i id="last" class="fa fa-step-forward pointer" data-pagetype="lastpage" data-pageframe=<%=pageframe %> data-func=<%=func %>></i>
      </div>
      <div class="arrow_L">
        <i id="next" class="fa fa-chevron-circle-right pointer" data-pagetype="nextpage" data-pageframe=<%=pageframe %> data-func=<%=func %>></i>
      </div>
      <div class="pageRow">
       	<span id="pageRow" data-type="row">1</span>
      </div>
      <div class="arrow_L">
        <i id="previous" class="fa fa-chevron-circle-left pointer" data-pagetype="prevpage" data-pageframe=<%=pageframe %> data-func=<%=func %>></i>
      </div>
      <div class="arrow_S">
        <i id="first" class="fa fa-step-backward pointer" data-pagetype="firstpage" data-pageframe=<%=pageframe %> data-func=<%=func %>></i>
      </div>
  </body>
</html>