$(document).ready(function() {
  $(function() {
    $("table").click(function(event) {
      var $target = $(event.target);
      $target.closest("tr").next().find("p").slideToggle();
    });
  });
});
