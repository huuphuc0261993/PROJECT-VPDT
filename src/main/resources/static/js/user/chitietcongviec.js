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
// chitietcongviec.showTitle = function () {
//     $.ajax(
//         {
//             url: urlPathHost + '/api/nhanvien/view',
//             method: 'GET',
//             dataType: 'json',
//             contentType: 'application/json',
//             success: function (data) {
//                 let t = $('#dataTable').DataTable({
//                     responsive: true
//                 });
//                 // index chỉ mục mảng , value giá trị của phần tử mảng
//                 $.each(data, function (index, value) {
//                     t.row.add([
//                         value.username,
//                         value.fullName,
//                         value.phongBan.tenPB,
//                         value.email,
//                         value.phone,
//                         "<i class='far fa-edit ' title='Chỉnh sửa' style='margin-right: 10px' onclick='nhanvien.edit(" + value.mnv + ")'></i>" +
//                         "<i class='far fa-trash-alt ' title='Xóa' style='margin-right: 10px' onclick='nhanvien.delete(" + value.mnv + ")'></i>"
//                     ]).draw();
//                 });
//
//
//             },
//             error: function (e) {
//                 console.log(e.message);
//             }
//         })
// }
// chitietcongviec.showBaoCao = function () {
//     $.ajax(
//         {
//             url: urlPathHost + '/api/chitietcongviec/view/'+id,
//             method: 'GET',
//             dataType: 'json',
//             contentType: 'application/json',
//             success: function (data) {
//                 console.log(data);
//                 let t = $('#dataTable').DataTable({
//                     responsive: true
//                 });
//                 // index chỉ mục mảng , value giá trị của phần tử mảng
//                 $.each(data, function (index, value) {
//                     console.log(value);
//                     t.row.add([
//                         value.congViec.tenCongViec,
//                         value.baoCao,
//                         value.baoCao,
//                         value.file
//                     ]).draw();
//                 });
//
//
//             },
//             error: function (e) {
//                 console.log(e.message);
//             }
//         })
// }
$(document).ready(function () {

    // chitietcongviec.showBaoCao();
});