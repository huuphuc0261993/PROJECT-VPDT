var chitietcongviec = chitietcongviec || {}

chitietcongviec.send = function (){

    var id;
    id = $("#idChiTiet").val();
        var formData = new FormData();
        formData.append("baoCao", $("#exampleFormControlTextarea1").val());
    var fileup = document.getElementById('customFile')
    var fileData = fileup.files[0];
        formData.append("filename",fileData);
    console.log(formData);
        $.ajax({
            url: urlPathHost + "/user/chitietcongviec/submit/"+id,
            type: "POST",
            data: formData,
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            success: function () {
                // Handle upload success
                // ...
                console.log("POST DONE");
                location.reload();
            },
            error: function () {
                // Handle upload error
                // ...
            }
        });
}
chitietcongviec.giaHan = function () {
    var id;
    var giaHanObject = {
        ngayGiaHan: '',
        ngayKetThuc:''
    };
    giaHanObject.ngayGiaHan = ($('#giaHan').val());
    giaHanObject.ngayKetThuc = ($('#ngayKetThuc').val());
    var ngayKetThuc = new Date(giaHanObject.ngayKetThuc);
    var ngayGiaHan = new Date(giaHanObject.ngayGiaHan);
    id = $("#idChiTiet").val();

    if(ngayGiaHan > ngayKetThuc){
        $.ajax({
            url: urlPathHost+"/api/chitietcongviec/giaHan/"+id,
            method: "PUT",
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(giaHanObject),
            success: function () {
                console.log("POST DONE");
                bootbox.alert({
                    title: "Gia Hạn",
                    message: "Yêu cầu của bạn đang chờ xét duyệt!",
                    backdrop: true,
                });
            },
        });
    }else {
        bootbox.alert({
            message: "Ngày gia hạn không được nhỏ hơn ngày hoàn thành",
            backdrop: true,
        });
    }

}

chitietcongviec.chuyenGiao = function () {
    // var id;
    // var giaHanObject = {
    //     ngayGiaHan: '',
    //     ngayKetThuc:''
    // };
    // giaHanObject.ngayGiaHan = ($('#giaHan').val());
    // giaHanObject.ngayKetThuc = ($('#ngayKetThuc').val());
    // var ngayKetThuc = new Date(giaHanObject.ngayKetThuc);
    // var ngayGiaHan = new Date(giaHanObject.ngayGiaHan);
    // id = $("#idChiTiet").val();
    //
    // if(ngayGiaHan > ngayKetThuc){
    //     $.ajax({
    //         url: urlPathHost+"/api/chitietcongviec/giaHan/"+id,
    //         method: "PUT",
    //         dataType: "json",
    //         contentType: "application/json",
    //         data: JSON.stringify(giaHanObject),
    //         success: function () {
    //             console.log("POST DONE");
    //             bootbox.alert({
    //                 title: "Gia Hạn",
    //                 message: "Yêu cầu của bạn đang chờ xét duyệt!",
    //                 backdrop: true,
    //             });
    //         },
    //     });
    // }else {
    //     bootbox.alert({
    //         message: "Ngày gia hạn không được nhỏ hơn ngày hoàn thành",
    //         backdrop: true,
    //     });
    // }
}
chitietcongviec.deXuatKetThuc = function () {
    var id;
    id = $("#idChiTiet").val();
    bootbox.confirm({
        title: "Đề xuất kết thúc công việc",
        message: "Bạn có muốn kết thúc công việc?",
        buttons: {
            cancel: {
                label: '<i class="fa fa-times"></i> Hủy'
            },
            confirm: {
                label: '<i class="fa fa-check"></i> Có',
                className: 'btn-success'
            }
        },
        callback: function (result) {
            if (result) {
                $.ajax({
                    url: urlPathHost+"/api/chitietcongviec/dexuat/" + id,
                    method: "PUT",
                    dataType: "json",
                    success: function () {
                        console.log("POST DONE");
                        location.reload();
                    }
                });
            }
        }
    });
}

$(document).ready(function () {

});