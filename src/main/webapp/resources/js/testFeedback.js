var flag = true;
function getFeedback(prod_id) {
    if (flag) {
        $.ajax({
            url: 'AllFeedbackForProduct',
            data: {id:prod_id},
            success: function (data) {
                var result = "<table><tbody>";
                $.each(data, function (k, v) {
                    result += "<tr>";
                    result += "<td><br><br>";
                    result += v.user_id;
                    result += "</td>";
                    result += "</tr>";
                    result += "<tr>";
                    result += "<td><br>";
                    result += v.content;
                    result += "</td>";
                });
                result += "</tbody></table><br>";
                $('#feedback_container').html(result);
                flag = false;
            },
            error: function () {
                alert('Error');
            }
        });
    }
    $(document.querySelector('.feedback_text').classList.remove('display_none'));
    $(document.querySelector('.desc_text').classList.add('display_none'));
}

