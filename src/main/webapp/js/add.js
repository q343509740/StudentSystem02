function add_submit() {
    var student_id = document.getElementById("student_id");
    var name = document.getElementById("name");
    var age = document.getElementById("age");
    var sex = document.getElementById("sex");
    var birthday = document.getElementById("birthday");
    
    var data = {};
    data["student_id"] = student_id.value;
    data["name"] = name.value;
    data["age"] = age.value;
    data["sex"] = sex.value;
    data["birthday"] = birthday.value;
    
    $.ajax({
        url:"add",
        type:"POST",
        data:data,
        dataType:"JSON",
        success:function (result) {
            if(result){
                alert("新增成功");
                setTimeout('window.location.href="listStudent.html"');
            }
        }
    });
}