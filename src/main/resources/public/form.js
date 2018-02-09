$(document).ready(function () {
    $('#visitTime').datetimepicker({
        format:'d.m.Y H:i',
        i18n:{
            et:{
                months:[
                    'Jaanuar','Veebruar','Märts',
                    'Aprill', 'Mai','Juuni',
                    'Juuli','August', 'September',
                    'Oktoober','November','Detsember'
                ],
                dayOfWeek:[
                    "P", "E", "T", "K", "N", "R", "L"
                ]
            }
        }
    });
});
