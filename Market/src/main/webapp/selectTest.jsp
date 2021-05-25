<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="select.css">
</head>
<body>
<!-- <div class="form-group"> -->
  <!-- <fieldset disabled=""> -->
    <!-- <label class="form-label" for="disabledInput">Disabled input</label> -->
    <input class="form-control" id="disabledInput" type="text" placeholder="Disabled input here..." disabled="">
  <!-- </fieldset> -->
  <fieldset disabled="">
    <label class="form-label" for="disabledInput">Disabled input</label>
    <input class="form-control" id="disabledInput" type="text" placeholder="Disabled input here..." disabled=""><br>
    <input class="form-control" id="disabledInput" type="text" placeholder="Disabled input here..." disabled="">
  </fieldset>
<!-- </div> -->
  <div class="form-group">
      <label for="exampleSelect1" class="form-label mt-4">Example select</label>
      <select class="form-select" id="exampleSelect1">
        <option>1</option>
        <option>2</option>
        <option>3</option>
        <option>4</option>
        <option>5</option>
      </select>
    </div>
    
    <!-- <div class="form-group">
  <label class="form-label mt-4">Input addons</label>
  <div class="form-group"> -->
    <div class="input-group mb-3">
      <span class="input-group-text">$</span>
      <input type="text" class="form-control" aria-label="Amount (to the nearest dollar)">
      <span class="input-group-text">.00</span>
    </div>
 <!--  </div>
</div> -->

<div class="form-group">
  <label class="col-form-label col-form-label-sm mt-4" for="inputSmall">Small input</label>
  <input class="form-control form-control-sm" type="text" placeholder=".form-control-sm" id="inputSmall">
</div>
    
    
</body>
</html>