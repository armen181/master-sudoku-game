
var started = false;
var endGame = false;
var count = 0;
 $(document).ready(function () {

     $("#btnForSudoku00, #btnForSudoku01,#btnForSudoku02, #btnForSudoku03, #btnForSudoku04, #btnForSudoku05,#btnForSudoku06,#btnForSudoku07,#btnForSudoku08, #btnForSudoku10, #btnForSudoku11,#btnForSudoku12, #btnForSudoku13, #btnForSudoku14, #btnForSudoku15,#btnForSudoku16,#btnForSudoku17,#btnForSudoku18,#btnForSudoku20, #btnForSudoku21,#btnForSudoku22, #btnForSudoku23, #btnForSudoku24, #btnForSudoku25,#btnForSudoku26,#btnForSudoku27,#btnForSudoku28, #btnForSudoku30, #btnForSudoku31,#btnForSudoku32, #btnForSudoku33, #btnForSudoku34, #btnForSudoku35,#btnForSudoku36,#btnForSudoku37,#btnForSudoku38,#btnForSudoku40, #btnForSudoku41,#btnForSudoku42, #btnForSudoku43, #btnForSudoku44, #btnForSudoku45,#btnForSudoku46,#btnForSudoku47,#btnForSudoku48,#btnForSudoku50, #btnForSudoku51,#btnForSudoku52, #btnForSudoku53, #btnForSudoku54, #btnForSudoku55,#btnForSudoku56,#btnForSudoku57,#btnForSudoku58,#btnForSudoku60, #btnForSudoku61,#btnForSudoku62, #btnForSudoku63, #btnForSudoku64, #btnForSudoku65,#btnForSudoku66,#btnForSudoku67,#btnForSudoku68, #btnForSudoku70, #btnForSudoku71,#btnForSudoku72, #btnForSudoku73, #btnForSudoku74, #btnForSudoku75,#btnForSudoku76,#btnForSudoku77,#btnForSudoku78,#btnForSudoku80, #btnForSudoku81,#btnForSudoku82, #btnForSudoku83, #btnForSudoku84, #btnForSudoku85,#btnForSudoku86,#btnForSudoku87,#btnForSudoku88"
     ).click(function () {
         alert($(this).attr("y")+"__" +$(this).attr("x"));

     });



     $("#start1").click(function () {
         var answer = 1;
         var settings = {
             "async": true,
             "crossDomain": true,
             "url": "startGame",
             "method": "POST",
             "headers": {
                 "mode": answer
             }
         }

         $.ajax(settings).done(function (response) {

             console.log(response);


         });
     });



     $("#beginGame").click(function () {
           if(!started) {
               var settings = {
                   "async": true,
                   "crossDomain": true,
                   "url": "startGame",
                   "method": "GET",
                   "headers": {}
               }

               $.ajax(settings).done(function (response) {

                   $('#questionTextarea').text(response.question);
                   $('#answerBtn1').text(response.answer_1);
                   $('#answerBtn2').text(response.answer_2);
                   $('#answerBtn3').text(response.answer_3);
                   $('#answerBtn4').text(response.answer_4);
                   $('#beginGame').text("Վերջ");
                   $('#use50_50').prop('disabled', false);
                   $('#beginGame').prop('disabled', false);
                   started = true;
               });
           }else {
               $("#answerBtn3, #answerBtn4").addClass("invisible");
               $('#questionTextarea').text("Խաղը ավարտված է, ցանկանում եք վերսկսել խաղը");
               $('#answerBtn1').text("Այո");
               $('#answerBtn2').text("Պահել արդյունքը");
               $('#use50_50').prop('disabled', true);
               $('#beginGame').prop('disabled', true);
               endGame=true;




           }
        });



     $("#answerBtn1, #answerBtn2, #answerBtn3, #answerBtn4 ").click(function () {
         if(!endGame) {



             var answer = $(this).val();
             var settings = {
                 "async": true,
                 "crossDomain": true,
                 "url": "answer",
                 "method": "POST",
                 "headers": {
                     "answer": answer
                 }
             }

             $.ajax(settings).done(function (response) {

                 if (response.questionId == "false") {
                     if(count>=3) {
                         count=3;
                         $("#answerBtn3, #answerBtn4,#answerBtn2").addClass("invisible");
                     }

                     else

                         {
                         count=0;
                         $("#answerBtn3, #answerBtn4").addClass("invisible");

                     }


                     $('#questionTextarea').text("Խաղը ավարտված է, ցանկանում եք վերսկսել խաղը");
                     $('#answerBtn1').text("Այո");
                     $('#answerBtn2').text("Պահել արդյունքը");
                     $('#use50_50').prop('disabled', true);
                     $('#beginGame').prop('disabled', true);
                     endGame=true;

                 }else if(response.questionId == "true"){
                     count++;
                     if(count>6) {
                         $("#answerBtn3, #answerBtn4").addClass("invisible");
                         $('#questionTextarea').text("Խաղը ավարտված է, ցանկանում եք վերսկսել խաղը");
                         $('#answerBtn1').text("Այո");
                         $('#answerBtn2').text("Պահել արդյունքը");
                         $('#use50_50').prop('disabled', true);
                         $('#beginGame').prop('disabled', true);
                         endGame = true;
                         count=0;
                     }else {
                         var settings = {
                             "async": true,
                             "crossDomain": true,
                             "url": "next",
                             "method": "GET",
                             "headers": {}
                         }

                         $.ajax(settings).done(function (response) {
                             $('#questionTextarea').text(response.question);
                             $('#answerBtn1').text(response.answer_1);
                             $('#answerBtn2').text(response.answer_2);
                             $('#answerBtn3').text(response.answer_3);
                             $('#answerBtn4').text(response.answer_4);

                     });
                     }

                 }

             });
         }


         else



             {

             if($(this).val()==1){
                 var settings = {
                     "async": true,
                     "crossDomain": true,
                     "url": "startGame",
                     "method": "GET",
                     "headers": {}
                 }

                 $.ajax(settings).done(function (response) {

                     $('#questionTextarea').text(response.question);
                     $("#answerBtn3, #answerBtn4 , #answerBtn2, #answerBtn1").removeClass("invisible");
                     $('#answerBtn1').text(response.answer_1);
                     $('#answerBtn2').text(response.answer_2);
                     $('#answerBtn3').text(response.answer_3);
                     $('#answerBtn4').text(response.answer_4);
                     $('#beginGame').text("Ավարտել");
                     $('#use50_50').prop('disabled', false);
                     $('#beginGame').prop('disabled', false);

                     started = true;
                     endGame = false;
                 });

             }else if($(this).val()==2){
                 $('#exampleModal').modal({
                     backdrop: 'static',
                     keyboard: false
                 }, 'show');
                 //$('#exampleModal').modal('hide');
             }


         }
     });





    });

