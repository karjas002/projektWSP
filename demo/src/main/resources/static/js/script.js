/*This is for the overlay to appear and disappear nicely.*/
$('.info').click(function (event) {
  var ne = $(this).next('div.overlay');
  event.preventDefault();
  ne.fadeToggle(300);
  return false;
});

$('.innerContainer').click(function (e) {
  e.stopImmediatePropagation();
});
$('body').click(function () {
  $('div.overlay').fadeOut(300);
});

$('.cancel').click(function () {
  $('div.overlay').fadeOut(300);
});
/*This is my disable/re-enable scroll script*/
$(function () {
  $('.info').click(function () {
    $('body').css('overflow', 'hidden');
  });
  $('body, .close').click(function () {
    $('body').css('overflow', 'visible');
  });
});
