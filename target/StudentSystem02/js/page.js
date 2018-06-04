function page() {

    $.ajax({
        url:"pageHelper",
        type:"POST",
        dataType:"JSON",
        success:function (result) {
            console.log(result);
            // alert(result[0].hasPreviousPage);
            // alert(result[0].hasNextPage);
            if(result[0].hasPreviousPage){
                document.getElementById("hasPre").style.visibility="visible";
            }
            if(result[0].hasNextPage){
                document.getElementById("hasNext").style.visibility="visible";
            }
            $("#postTable").html("<tr style='font-weight: bold'><td>学号</td><td>姓名</td><td>年龄</td><td>性别</td><td>出生年月日</td><td>编辑</td><td>删除</td></tr>");
            $("#postTable").append("<caption id='total'></caption>");
            $.each(result, function (i, item) {
                var birthday = new Date(item.birthday);
                var date = birthday.getFullYear() + '-' + fix((birthday.getMonth()+1),2) + '-' + fix(birthday.getDate(),2);
                $("#postTable").append(
                    '<tr id="list">' +
                    '<td>' + item.student_id + '</td>' +
                    '<td>' + item.name + '</td>' +
                    '<td>' + item.age + '</td>' +
                    '<td>' + item.sex + '</td>' +
                    '<td>' + date + '</td>' +
                    '<td><a href="editStudent.html?id='+ item.id + '"><span class="glyphicon glyphicon-edit">编辑</span></a> </td>' +
                    '<td><a href="#" id="'+item.id+'" onclick="del(this)"><span class="glyphicon glyphicon-trash">删除</span></a> </td>' +
                    '</tr>'
                );
            })
        },
        error:function () {
            alert("请求出错!");
        }
    });
}

//其中赋值的数字都是两位数，不足两位的话需要用0来填充
function fix(num, length) {
    return ('' + num).length < length ? ((new Array(length + 1)).join('0') + num).slice(-length) : '' + num;
}
