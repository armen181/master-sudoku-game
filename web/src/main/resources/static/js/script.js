
var started = false;
var endGame = false;
var count = 0;
 $(document).ready(function () {

     $("#saveNameBtn").click(function () {
         //Send to DB and save name - score
         $("#answerBtn2").addClass("invisible");
         $('#exampleModal').modal('hide');
     });



     $("#use50_50").click(function () {
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

             $('#questionTextarea').text(response.question);
             if(response.answer_1=="_________"){
                 $('#answerBtn1').text("-");
                 $('#answerBtn1').prop('disabled', true);
             }
             if(response.answer_2=="_________"){
                 $('#answerBtn2').text("-");
                 $('#answerBtn2').prop('disabled', true);
             }
             if(response.answer_3=="_________"){
                 $('#answerBtn3').text("-");
                 $('#answerBtn3').prop('disabled', true);
             }
             if(response.answer_4=="_________"){
                 $('#answerBtn4').text("-");
                 $('#answerBtn4').prop('disabled', true);
             }

             $('#use50_50').prop('disabled', true);


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