// Code goes here

var marsAPI = "http://mars.mmacedo.eu.org/rest/mars/";
var cb = '&callback=?';

$(function() {
  "use strict";

  function a(a) {
    $(a).is(":checked") ? $(a).next().css("text-decoration", "line-through") : $(a).next().css("text-decoration", "none")
  }

  function b() {
    document.fullScreenElement && null !== document.fullScreenElement || !document.mozFullScreen && !document.webkitIsFullScreen ? document.documentElement.requestFullScreen ? document.documentElement.requestFullScreen() : document.documentElement.mozRequestFullScreen ? document.documentElement.mozRequestFullScreen() : document.documentElement.webkitRequestFullScreen && document.documentElement.webkitRequestFullScreen(Element.ALLOW_KEYBOARD_INPUT) : document.cancelFullScreen ? document.cancelFullScreen() : document.mozCancelFullScreen ? document.mozCancelFullScreen() : document.webkitCancelFullScreen && document.webkitCancelFullScreen()
  }

  function c() {
    try {
      return document.createEvent("TouchEvent"), !0
    } catch (a) {
      return !1
    }
  }
  var d = $(window).width();
  $(window).load(function() {
    setTimeout(function() {
      $("body").addClass("loaded")
    }, 200)
  }), $(".header-search-input").focus(function() {
    $(this).parent("div").addClass("header-search-wrapper-focus")
  }).blur(function() {
    $(this).parent("div").removeClass("header-search-wrapper-focus")
  }), $("#task-card input:checkbox").each(function() {
    a(this)
  }), $("#task-card input:checkbox").change(function() {
    a(this)
  }), $("select").material_select();
  var e = document.getElementById("indeterminate-checkbox");
  null !== e && (e.indeterminate = !0), $(".slider").slider({
    full_width: !0
  }), $(".dropdown-button").dropdown({
    inDuration: 300,
    outDuration: 125,
    constrain_width: !0,
    hover: !1,
    alignment: "left",
    gutter: 0,
    belowOrigin: !0
  }), $(".translation-button").dropdown({
    inDuration: 300,
    outDuration: 225,
    constrain_width: !1,
    hover: !0,
    gutter: 0,
    belowOrigin: !0,
    alignment: "left"
  }), $(".notification-button").dropdown({
    inDuration: 300,
    outDuration: 225,
    constrain_width: !1,
    hover: !0,
    gutter: 0,
    belowOrigin: !0,
    alignment: "left"
  }), $(".tab-demo").show().tabs(), $(".tab-demo-active").show().tabs(), $(".parallax").parallax(), $(".modal-trigger").leanModal({
    dismissible: !0,
    opacity: .5,
    in_duration: 300,
    out_duration: 200,
    ready: function() {},
    complete: function() {}
  }), $(".scrollspy").scrollSpy(), $(".tooltipped").tooltip({
    delay: 50
  }), $(".sidebar-collapse").sideNav({
    edge: "left"
  }), $(".menu-sidebar-collapse").sideNav({
    menuWidth: 240,
    edge: "left",
    menuOut: !1
  }), $(".dropdown-menu").dropdown({
    inDuration: 300,
    outDuration: 225,
    constrain_width: !1,
    hover: !0,
    gutter: 0,
    belowOrigin: !0
  }), $(".chat-collapse").sideNav({
    menuWidth: 300,
    edge: "right"
  }), $(".chat-close-collapse").click(function() {
    $(".chat-collapse").sideNav("hide")
  }), $(".chat-collapsible").collapsible({
    accordion: !1
  }), $(".datepicker").pickadate({
    selectMonths: !0,
    selectYears: 15
  }), $("select").not(".disabled").material_select();
  var f = $(".page-topbar").height(),
    g = window.innerHeight - f;
  $(".leftside-navigation").height(g).perfectScrollbar({
    suppressScrollX: !0
  });
  $("#rover-collection").height(400).perfectScrollbar({
    suppressScrollX: !0
  });
  var h = $("#chat-out").height();
  $(".rightside-navigation").height(h).perfectScrollbar({
    suppressScrollX: !0
  }), $(".toggle-fullscreen").click(function() {
    b()
  }), $("nav").length ? $(".toc-wrapper").pushpin({
    top: $("nav").height()
  }) : $("#index-banner").length ? $(".toc-wrapper").pushpin({
    top: $("#index-banner").height()
  }) : $(".toc-wrapper").pushpin({
    top: 0
  });
  var i = $("#flow-toggle");
  i.click(function() {
    $("#flow-text-demo").children("p").each(function() {
      $(this).toggleClass("flow-text")
    })
  }), $("#card-alert .close").click(function() {
    $(this).closest("#card-alert").fadeOut("slow")
  });
  var j = $("#container-toggle-button");
  j.click(function() {
    $("body .browser-window .container, .had-container").each(function() {
      $(this).toggleClass("had-container"), $(this).toggleClass("container"), $(this).hasClass("container") ? j.text("Turn off Containers") : j.text("Turn on Containers")
    })
  }), c() && $("#nav-mobile").css({
    overflow: "auto"
  }), 480 >= d && $("#trending-line-chart").attr({
    height: "200"
  })
});

function checkStatus(response) {
  if (response.status >= 200 && response.status < 300) {
    return response
  } else {
    var error = new Error(response.statusText)
    error.response = response
    throw error
  }
}

function parseText(response) {
  return response.text()
}

function parseJSON(response) {
  return response.json()
}

function generatePositionItem(params) {
  var template = '<li class="collection-item"> <div> \
  <span class="title">Command: {{c}} X: {{x}} Y: {{y}} \
  <a href="#!" class="secondary-content"><i class="material-icons">{{icon}}</i></a></span> \
  </div></li>  \
  ';
  return Mustache.render(template, params);

}

function handleRoverResults(data, command) {
  var x = data.charAt(1);
  var y = data.charAt(3);
  var o = data.charAt(5);
  var icon = "";
  
  switch (o) {
    case 'N': icon = "arrow_upward"; break;
    case 'S': icon = "arrow_downward"; break;
    case 'W': icon = "arrow_back"; break;
    case 'E': icon = "arrow_forward"; break;
    default : icon = "";
  }
  
  var params = { "x" : x, "y": y, "icon": icon, "c": command};
  var positionItem = generatePositionItem(params);
  var e = document.createElement('div');
  e.innerHTML = positionItem;
  var pos = document.getElementById("rover-results");
  pos.appendChild(e.firstChild);
}

function getRoverSearchResults(command) {

  fetch(marsAPI + command, {
    method: 'post'
    })
    .then(checkStatus)
    .then(parseText)
    .then(function(data) {
      console.log('request succeeded with response', data);
      handleRoverResults(data, command);
    }).catch(function(error) {
      console.log('request failed', error);
      Materialize.toast(Mustache.render('<a class=&quot;btn red &quot; href=&quot;#!&quot;>{{error}}<a>', { "error" : error}), 5000);
    });
}

function handleRoverSearch(form) {
  event.preventDefault();
  var input = form.elements[0].value;
  getRoverSearchResults(input);
}

$(document).ready(function() {

});