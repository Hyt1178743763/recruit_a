

function toForm(){
    var fileObj = document.getElementById("file").files[0]; // js 获取文件对象
    $.ajax({
        type: "POST",
        url: "http://10.85.16.69:8899/recruit/table/add1",
        data:{
            "name":$("#name").val(),
            "gender": $("#gender").val(),
            "phonenum": $("#phonenum").val(),
            "qqnum": $("#qqnum").val() ,
            "classes": $("#classes").val(),
            "dormitory": $("#dormitory").val(),
            "organization": $("#organization").val(),
            "introduction": $("#introduction").val(),
            "likes": $("#likes").val() ,
            "future": $("#future").val(),
            "traget": $("#traget").val(),
            "file": fileObj
        },
        dataType: "json",//预期服务器返回的数据类型
        async: false,
        error: function() {
            alert("连接服务器失败！");
        },
    });
}


$("#file").fileinput({
    uploadUrl: 'http://10.85.16.69:8899/recruit/table/add1', // you must set a valid URL here else you will get an error
    allowedFileExtensions : ['jpg', 'png','jpeg'],
    overwriteInitial: false,
    maxFileSize: 1000,
    maxFilesNum: 1,
    //allowedFileTypes: ['image', 'video', 'flash'],
    slugCallback: function(filename) {
        return filename.replace('(', '_').replace(']', '_');
    }
});
