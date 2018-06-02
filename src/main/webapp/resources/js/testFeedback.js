function getFeedback(prod_id) {
    $.ajax({
        url: 'AllFeedbackForProduct',
        data: {id:prod_id},
        success: function (data) {
            var result = "<table><tbody>";
            $.each(data, function (k, v) {
                result += "<tr>";
                result += "<td style='font-style: oblique; text-align: left; font-weight: bold; font-size: 16px'><br><br>";
                result += v.user.name;
                result += "</td>";
                result += "<td style='font-style: oblique; text-align: right; font-size: 16px'><br><br>";
                result += "Rating: " + v.rating;
                result += "</td>";
                result += "</tr>";
                result += "<tr>";
                result += "<td><br>";
                result += v.content;
                result += "</td>";
                result += "</tr>";
            });
            result += "</tbody></table><br>";
            $('#feedback_container').html(result);
        },
        error: function (data) {
            console.log(data);
        }
    });
    $(document.querySelector('.feedback_text').classList.remove('display_none'));
    $(document.querySelector('.desc_text').classList.add('display_none'));
}

