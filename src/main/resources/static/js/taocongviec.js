var taocongviec = taocongviec || {}
var nhanVienLamViec = []
taocongviec.listphongban = function () {
    $.ajax(
        {
            url: urlPathHost + '/api/phongban/view',
            method: 'GET',
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {
                // phongBanList = data;
                $('#phongBanId').html("<option disabled selected>--/--</option>");
                // index chỉ mục mảng , value giá trị của phần tử mảng
                $.each(data, function (index, value) {
                    $('#phongBanId').append(
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

taocongviec.phongban = function (element) {
    let idPhongBan = $(element).val();
    console.log(idPhongBan);
    $.ajax(
        {
            url: urlPathHost + '/api/nhanvien/view/' + idPhongBan,
            method: 'GET',
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {
                console.log(data);
                $('#nhanVienId').html("");
                // nhanVienList = data;
                // index chỉ mục mảng , value giá trị của phần tử mảng
                $.each(data, function (index, value) {
                    if (nhanVienLamViec.indexOf(value.mnv) == -1) {
                        $('#nhanVienId').append(
                            "<option value='" + value.mnv + "'>" + value.fullName + "</option>"
                        );
                    }

                });
            },
            error: function (e) {
                console.log(e.message);
            }
        });
}

var inNhavien = [];
taocongviec.showNhanVien = function () {
    taocongviec.phongban($('#phongBanId'));
    let t = $('#dataTable').DataTable();
    t.clear().draw();
    $.each(inNhavien, function (index, value) {
        t.row.add([
            value[1],
            value[2],
            `<input type='radio' name='iMain' value='${value[0]}'>`,
            `<span onclick='taocongviec.xoa(${index})'><i class='far fa-trash-alt' ></i></span>`
        ]).draw();

    });
}

taocongviec.themtatca = function () {
    let idPhongBan = $('#phongBanId').val();
    let idNhanVien = $('#nhanVienId').val();
    let nhanvien = $('#nhanVienId').find(`option[value='${idNhanVien}']`).text();
    console.log(nhanvien);
    if (nhanvien == "") {
        alert("Thêm không thành công")
    } else {
        $.ajax(
            {
                url: urlPathHost + '/api/nhanvien/view/' + idPhongBan,
                method: 'GET',
                dataType: 'json',
                contentType: 'application/json',
                success: function (data) {
                    // index chỉ mục mảng , value giá trị của phần tử mảng
                    $.each(data, function (index, value) {
                        if (nhanVienLamViec.indexOf(value.mnv) == -1) {
                            nhanVienLamViec.push(value.mnv);

                            inNhavien.push([value.mnv, value.phongBan.tenPB, value.fullName])
                        }
                    });
                    // console.log(inNhavien);
                    console.log(nhanVienLamViec);
                    taocongviec.showNhanVien();
                },
                error: function (e) {
                    console.log(e.message);
                }
            })
    }
}


taocongviec.them = function () {
    let idPhongBan = $('#phongBanId').val();
    let phongban = $('#phongBanId').find(`option[value='${idPhongBan}']`).text();
    let idNhanVien = $('#nhanVienId').val();
    let nhanvien = $('#nhanVienId').find(`option[value='${idNhanVien}']`).text();

    if (nhanvien == "") {
        alert("Thêm không thành công")
    } else {
        nhanVienLamViec.push(+idNhanVien);
        inNhavien.push([+idNhanVien, phongban, nhanvien]);

        taocongviec.showNhanVien();
    }
}

taocongviec.xoa = function (index) {
    nhanVienLamViec.splice(index, 1);
    inNhavien.splice(index, 1);
    taocongviec.showNhanVien();

}
//kiểm tra
// document.querySelectorAll("input[name='iMain']")[0].checked

        taocongviec.save = function () {
            var congViecObject = {
                tenCongViec: '',
                noiDung: '',
                ngayBatDau: '',
                ngayKetThuc: '',
                tatCaNhanVien: [],
                nvChinh: ''
            };

            let tmp = document.querySelectorAll("input[name='iMain']");
            $.each(tmp, function (index, value) {
                console.log(tmp[index].checked);
                if (tmp[index].checked === true) {
                    congViecObject.nvChinh = tmp[index].value;
                }
            });
            congViecObject.tenCongViec = ($('#title-work').val());
            congViecObject.noiDung = ($('#message-text').val());
            congViecObject.ngayBatDau = ($('#start-day').val());
            congViecObject.ngayKetThuc = ($('#end-day').val());
            congViecObject.tatCaNhanVien = nhanVienLamViec;
            console.log(congViecObject);
            // $.ajax({
            //     url: urlPathHost + "/api/taocongviec/create",
            //     method: "POST",
            //     dataType: "json",
            //     contentType: "application/json",
            //     data: JSON.stringify(congViecObject),
            //     success: function () {
            //         console.log("POST DONE");
            //         $('#tBody').empty();
            //         $('#dataTable').dataTable().fnClearTable();
            //         $('#dataTable').dataTable().fnDestroy();
            //
            //     },error: function (e) {
            //         console.log(e.message);
            //     },
            // });

            $.ajax({
                url: urlPathHost + "/api/taocongviec/create",
                method: "POST",
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(congViecObject),
                success: function () {
                    console.log("POST DONE");
                    location.reload();
                }
            })
        }

$(document).ready(function () {
    taocongviec.listphongban();
});