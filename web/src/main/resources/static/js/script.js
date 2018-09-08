
var x = 0;
var y = 0;
var mode = 0;
 $(document).ready(function () {

     $("#btnForSudoku00, #btnForSudoku01,#btnForSudoku02, #btnForSudoku03, #btnForSudoku04, #btnForSudoku05,#btnForSudoku06,#btnForSudoku07,#btnForSudoku08, #btnForSudoku10, #btnForSudoku11,#btnForSudoku12, #btnForSudoku13, #btnForSudoku14, #btnForSudoku15,#btnForSudoku16,#btnForSudoku17,#btnForSudoku18,#btnForSudoku20, #btnForSudoku21,#btnForSudoku22, #btnForSudoku23, #btnForSudoku24, #btnForSudoku25,#btnForSudoku26,#btnForSudoku27,#btnForSudoku28, #btnForSudoku30, #btnForSudoku31,#btnForSudoku32, #btnForSudoku33, #btnForSudoku34, #btnForSudoku35,#btnForSudoku36,#btnForSudoku37,#btnForSudoku38,#btnForSudoku40, #btnForSudoku41,#btnForSudoku42, #btnForSudoku43, #btnForSudoku44, #btnForSudoku45,#btnForSudoku46,#btnForSudoku47,#btnForSudoku48,#btnForSudoku50, #btnForSudoku51,#btnForSudoku52, #btnForSudoku53, #btnForSudoku54, #btnForSudoku55,#btnForSudoku56,#btnForSudoku57,#btnForSudoku58,#btnForSudoku60, #btnForSudoku61,#btnForSudoku62, #btnForSudoku63, #btnForSudoku64, #btnForSudoku65,#btnForSudoku66,#btnForSudoku67,#btnForSudoku68, #btnForSudoku70, #btnForSudoku71,#btnForSudoku72, #btnForSudoku73, #btnForSudoku74, #btnForSudoku75,#btnForSudoku76,#btnForSudoku77,#btnForSudoku78,#btnForSudoku80, #btnForSudoku81,#btnForSudoku82, #btnForSudoku83, #btnForSudoku84, #btnForSudoku85,#btnForSudoku86,#btnForSudoku87,#btnForSudoku88"
     ).click(function () {
         x=$(this).attr("x");
         y=$(this).attr("y");
         $('#btnModel').modal({
             backdrop: 'static',
             keyboard: false
         }, 'show');

     });
     $("#btn1, #btn2, #btn3, #btn4,#btn5,#btn6,#btn7, #btn8,#btn9").click(function () {

         var val = $(this).attr("val");
         var settings = {
             "async": true,
             "crossDomain": true,
             "url": "setValue",
             "method": "POST",
             "headers": {
                 "x": y,
                 "y": x,
                 "value": val
             }
         }
         $.ajax(settings).done(function (response) {


             update(response);
             $('#btnModel').modal('hide')

         });


     });



     $("#start1,#start2,#start3").click(function () {
         mode = $(this).attr("val");
         var settings = {
             "async": true,
             "crossDomain": true,
             "url": "startGame",
             "method": "POST",
             "headers": {
                 "mode": mode
             }
         }

         $.ajax(settings).done(function (response) {


             update(response);
             $('#reLoad, #end, #check').prop('disabled', false);
             $('#startAll').prop('disabled', true);



         });
     });

     $("#reLoad").click(function () {
         var settings = {
             "async": true,
             "crossDomain": true,
             "url": "reset",
             "method": "POST",
             "headers": {
                 "mode": mode
             }
         }

         $.ajax(settings).done(function (response) {


             update(response);
             $('#reLoad, #end, #check').prop('disabled', false);
             $('#startAll').prop('disabled', true);



         });
     });

     $("#end").click(function () {
         var settings = {
             "async": true,
             "crossDomain": true,
             "url": "end",
             "method": "GET",
             "headers": {
             }
         }

         $.ajax(settings).done(function (response) {


             update(response);
             $('#reLoad, #end, #check').prop('disabled', true);
             $('#startAll').prop('disabled', false);



         });
     });


 });



function update(array) {

    for (var i = 0; i < 9; i++) {
        for (var j = 0; j < 9; j++) {

            var id = "#btnForSudoku" + i + j;
            $(id).text(array[i][j].value);
            if (!array[i][j].change) {
                $(id).attr('disabled', 'disabled');
            }else {
                $(id).removeAttr('disabled', 'disabled');
            }
            if(array[i][j].correct){
                $(id).addClass("btn-danger");
            }else {
                $(id).removeClass("btn-danger");

            }



        }
    }
}