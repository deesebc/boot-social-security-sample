<%@ include file="../tiles/view/taglib.jsp"%>
<form  class="form-signin" action="${pageContext.request.contextPath}/login" method="post">
  <h2 class="form-signin-heading">Please sign in</h2>
  <label for="inputEmail" class="sr-only">Email address</label>
  <input type="email" id="inputEmail" class="form-control" placeholder="Email address" name="username" required autofocus>
  <label for="inputPassword" class="sr-only">Password</label>
  <input type="password" id="inputPassword" class="form-control" placeholder="Password" name="password" required >
  <div class="checkbox">
    <label>
      <input type="checkbox" value="remember-me"> Remember me
    </label>
  </div>
  <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
<form class="form-signin" id="facebookForm" action="/auth/facebook">
  <input type="hidden" name="scope" value="email, public_profile" />
  <h2 class="form-signin-heading">Please auth with Facebook</h2>
  <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
</form>
