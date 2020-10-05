var nhanvien = nhanvien || {}
nhanvien.showTitle = function (){
    $.ajax(
        {
            url: urlPathHost+'/api/nhanvien/view' ,
            method: 'GET',
            dataType: 'json',
            contentType: 'application/json',
            success: function (data){
                let t = $('#dataTable').DataTable({
                    responsive: true
                });
                // index chỉ mục mảng , value giá trị của phần tử mảng
                $.each(data, function( index, value ) {
                    t.row.add( [
                        value.username,
                        value.fullName,
                        value.phongBan.tenPB,
                        value.email,
                        value.phone,
                        "<i class='far fa-edit ' title='Chỉnh sửa' style='margin-right: 10px' onclick='nhanvien.edit("+value.mpb+")'></i>"+
                        "<i class='far fa-trash-alt ' title='Xóa' style='margin-right: 10px' onclick='nhanvien.delete("+value.mpb+")'></i>"+
                        "<i class='fas fa-power-off ' title='Xóa' onclick='nhanvien.delete("+value.mpb+")'></i>"
                    ]).draw();
                });


            },
            error: function (e) {
                console.log(e.message);
            }
        })
}
nhanvien.listphongban = function () {
    $.ajax(
        {
            url: urlPathHost + '/api/phongban/view',
            method: 'GET',
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {
                console.log(data);
                // phongBanList = data;
                $('#department').html("<option disabled selected>--/--</option>");
                // index chỉ mục mảng , value giá trị của phần tử mảng
                $.each(data, function (index, value) {
                    $('#department').append(
                        "<option value='" + value.mpb + "' >" + value.tenPB + "</option>"
                    );
                });
            },
            error: function (e) {
                console.log(e);
                console.log(e.message);
            }
        }).done(function (data) {
        // If successful
        console.log(data);
    })
}

nhanvien.save = function () {
    var nhanvienObject = {};
    nhanvienObject.mnv = $('#id').val();
    nhanvienObject.username = $('#username').val();
    nhanvienObject.email = $('#email').val();
    nhanvienObject.password = $('#password').val();
    nhanvienObject.fullName = $('#fullName').val();
    nhanvienObject.department = $('#department').val();
    console.log(nhanvienObject.department);
    nhanvienObject.phone = $('#phone').val();
    nhanvienObject.avatar = $('#exampleFormControlFile1').val();
    console.log(nhanvienObject);
    if ( nhanvienObject.mnv === "") {
        console.log('quay lai');
        $.ajax({
            url: urlPathHost+'/api/nhanvien/create/'+nhanvienObject.department,
            method: 'POST',
            dataType: 'JSON',
            contentType: 'application/json',
            data: JSON.stringify(nhanvienObject),
            success: function (data) {
                console.log("POST DONE");
                $('#exampleModal').modal('hide');
                $('#tBody').empty();
                $('#dataTable').dataTable().fnClearTable();
                $('#dataTable').dataTable().fnDestroy();
                nhanvien.showTitle();
            }
        })
    }
    // else {
    //     $.ajax({
    //         url: urlPathHost+"/api/nhanvien/edit" ,
    //         method: "PUT",
    //         dataType: "json",
    //         contentType: "application/json",
    //         data: JSON.stringify(nhanvienObject),
    //         success: function () {
    //             $('#exampleModal').modal('hide');
    //             $('#tBody').empty();
    //             $('#dataTable').dataTable().fnClearTable();
    //             $('#dataTable').dataTable().fnDestroy();
    //             nhanvien.showTitle();
    //
    //         },
    //     });
    // }
}
nhanvien.edit = function(mpb){
    console.log('get :'+ mpb);
    $.ajax({
        url : urlPathHost+"/api/nhanvien/edit/" + mpb,
        method : "GET",
        dataType : "json",
        success : function(data){
            console.log(data);
            $('#myform')[0].reset();
            // //
            $('#exampleModalLabel').html("Chỉnh sửa phòng ban");
            $('#modal-form-1').html("Sửa");
            $('#id').val(data.mpb);
            $('#tenPB').val(data.tenPB);
            $('#exampleModal').modal('show');
            // $('#productLine').val(data.productLine.id);
            // $('#id').val(data.id);

        }
    });
};
nhanvien.resetForm = function () {
    $('#myform')[0].reset();
    $('#tenPB').val('');
    //
}
nhanvien.addNew = function () {
    $('#exampleModalLabel').html("Thêm nhân viên");
    nhanvien.resetForm();
    $('#modal-form-1').html("Tạo");
    $('#exampleModal').modal('show');
};
$(document).ready(function () {
    nhanvien.showTitle();
    nhanvien.listphongban();
});
