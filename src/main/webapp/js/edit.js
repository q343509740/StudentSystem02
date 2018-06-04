function edit(url) {

    var id = url.substr(4);

    var data = {};
    data["id"] = id;

    $.ajax({
        url:"get",
        type:"POST",
        data:data,
        dataType:"JSON",
        success:function (result) {
            console.log(result);
            $.each(result, function (i, item) {
                var birthday = new Date(item.birthday);
                var date = birthday.getFullYear() + '-' + fix((birthday.getMonth()+1),2) + '-' + fix(birthday.getDate(),2);
                document.getElementById("student_id").value=item.student_id;
                document.getElementById("name").value=item.name;
                document.getElementById("age").value=item.age;
                document.getElementById("sex").value=item.sex;
                document.getElementById("birthday").value=date;
            });
        }
    });
}

//其中赋值的数字都是两位数，不足两位的话需要用0来填充
function fix(num, length) {
    return ('' + num).length < length ? ((new Array(length + 1)).join('0') + num).slice(-length) : '' + num;
}



function edit_submit() {
    var id = document.getElementById("id");
    var student_id = document.getElementById("student_id");
    var name = document.getElementById("name");
    var age = document.getElementById("age");
    var sex = document.getElementById("sex");
    var birthday = document.getElementById("birthday");

    var data = {};
    data["id"] = id.value;
    data["student_id"] = student_id.value;
    data["name"] = name.value;
    data["age"] = age.value;
    data["sex"] = sex.value;
    data["birthday"] = birthday.value;

    $.ajax({
        url:"update",
        type:"POST",
        data:data,
        dataType:"JSON",
        success:function (result) {
            if(result){
                alert("修改成功")
                setTimeout('window.location.href="listStudent.html"');
            }
        }
    });
}

