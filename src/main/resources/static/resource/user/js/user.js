layui.use(["layer"], function () {
  var layer = layui.layer;
});

// 我的视频
elyasAjax("video/findByModal").then((res) => {
  let list = res.data.list;
  let html = "";

  list.forEach((element) => {
    html += `<div class="my-video-item" onclick="toVideoDetail(${element.id})">
        <img src="${element.imgUrl}" alt="">
        <div class="item-title">${element.name}</div>
        <div class="icons-list">
            <div class="icons-item">
                <i class="fas fa-play"></i>
                 <p> ${element.playNum}</p>
            </div>

            <div class="icons-item">
                 <p>${dateString(element.createdAt)}</p>
            </div>
        </div>
    </div>`;
  });

  $("#myallvideo").append(html);
  $("#myvi").text("我的视频 " + list.length);
});

// 我的收藏
elyasAjax("video/findByModal").then((res) => {
  let list = res.data.list;
  let html = "";

  list.forEach((element) => {
    html += `<div class="my-video-item" onclick="toVideoDetail(${element.id})">
        <img src="${element.imgUrl}" alt="">
        <div class="item-title">${element.name}</div>
        <div class="icons-list">
            <div class="icons-item">
                <i class="fas fa-play"></i>
                 <p>${element.playNum}</p>
            </div>

            <div class="icons-item">
                 <p>${dateString(element.createdAt)}</p>
            </div>
        </div>
    </div>`;
  });

  $("#mySaveAll").append(html);
  $("#myvs").text("收藏 " + list.length);
});

// 收藏页面
elyasAjax("video/findByModal").then((res) => {
  let list = res.data.list;
  let html = "";

  list.forEach((element) => {
    html += `<div class="my-video-item" onclick="toVideoDetail(${element.id})">
        <img src="${element.imgUrl}" alt="">
        <div class="item-title">${element.name}</div>
        <div class="icons-list">
            <div class="icons-item">
                <i class="fas fa-play"></i>
                 <p>${element.playNum}</p>
            </div>

            <div class="icons-item">
                 <p>${dateString(element.createdAt)}</p>
            </div>
        </div>
    </div>`;
  });

  $("#pageSave").append(html);
  $("#pgsavenum").text("我的收藏 " + list.length);
});


// 视频页面

let typeId = ''
let detailURL = 'video/type/detail?id='
let allURL = 'video/findByModal'
getTypeDataList(typeId)
function getTypeDataList(id) {
    if (id == "" || id == undefined) {
        elyasAjax(allURL).then(res =>{
            let list  = res.data.list;
            let html = "";
            $('#pageVideoMy').html('')
            list.forEach(item=>{
                html+= ` <div class="my-video-item" onclick="toVideoDetail(${item.id})">
                <img src="${item.imgUrl}" alt="">
                <div class="item-title">${item.name}</div>
                <div class="icons-list">
                    <div class="icons-item">
                        <i class="fas fa-play"></i>
                         <p>${item.playNum}</p>
                    </div>
    
                    <div class="icons-item">
                         <p>${dateString(item.createdAt)}</p>
                    </div>
                </div>
            </div>`
            })
        
            $('#pageVideoMy').append(html)
        })
    }else {
        elyasAjax(detailURL+id).then(res =>{
            let list  = res.data.videoList;
            let html = "";
            $('#pageVideoMy').html('')
            list.forEach(item=>{
                html+= ` <div class="my-video-item" onclick="toVideoDetail(${item.id})">
                <img src="${item.imgUrl}" alt="">
                <div class="item-title">${item.name}</div>
                <div class="icons-list">
                    <div class="icons-item">
                        <i class="fas fa-play"></i>
                         <p>${item.playNum}</p>
                    </div>
    
                    <div class="icons-item">
                         <p>${dateString(item.createdAt)}</p>
                    </div>
                </div>
            </div>`
            })
        
            $('#pageVideoMy').append(html)
        })
    }
   
}

getTypeAll()
function getTypeAll() {
    elyasAjax('video/type/findByModal').then(res =>{
        let list  = res.data.list;
        let html = "";
        list.unshift({name:'全部',id:''})
  
        list.forEach(item=>{
            html+= `<li class="${item.id == typeId ? 'tabz active' : 'tabz'}" onclick="selectType(${item.id})">
            <span>${item.name}</span>
            
        </li>`
        })
    
        $('#samleft').html(html)
    })
}



function selectType(id) {
    typeId = id;
    getTypeDataList(id)
    getTypeAll()
}


// 个人信息
var avatar = []
let userInfo = {}
getData()
function getData() {
    elyasAjax('user/detailByToken').then(res =>{
        userInfo = res.data;
        $('#userName').val(res.data.userName)
        $('#password').val(res.data.password)
        $('#email').val(res.data.email)
        $('#phone').val(res.data.phone)
        avatar.push(res.data.avatar)
        $('.imgs').append('<img src="'+res.data.avatar.split(',')[res.data.avatar.split(',').length-1]+'" alt="" style="width: 100px;height:100px;margin: 10px;object-fit: cover;border-radius: 10px" id="prewImg"> <div class="delete" style="color:#000;background-color:#e5e5e5;font-size:14px;width:30px;height:15px;text-align:center;line-height:15px;cursor: pointer;">X</div>')
    
    })
}

$('#myFileUpload').change((event) => {

    if ($('.imgs img').length >= 1) {
        alert('最多上传1张')
        return
    }

    for (let index = 0; index < 1; index++) {

        const file = event.currentTarget.files[index]
        const formData = new FormData()
        formData.append('file', file, file.name)

        elyasAjax1('/file/uploadSingle', formData).then(res => {
            console.log('upload', JSON.parse(res).data);
            // 这里显示就需要重新设置一下了
            // $('#prewImg').attr('src',JSON.parse(res).data)

            $('.imgs').append('<img src="'+JSON.parse(res).data+'" alt="" style="width: 100px;height:100px;margin: 10px;object-fit: cover;border-radius: 10px" id="prewImg"> <div class="delete" style="color:#000;background-color:#e5e5e5;font-size:14px;width:30px;height:15px;text-align:center;line-height:15px;cursor: pointer;">X</div>')
            
        })
        // let isImg = $('.imgs')[index]
    }


    })

    // 删除图片
    $('.form_item').on('click', '.delete', function() {
        $(this).prev().remove()
        $(this).remove()
    })

    
    function reset(){
        const _avatar = []
        const imgs = $('.imgs img')
        for (i = 0; i < imgs.length; i++) {
            _avatar.push(imgs.eq(i).attr('src'))
        }

    
        elyasAjax('user/update',JSON.stringify({
            userName:$('#userName').val(),
            password:$('#password').val(),
            email:$('#email').val(),
            phone:$('#phone').val(),
            avatar:_avatar.toString(),
            id:userInfo.id
        })).then(res =>{
            console.log(res,'提交信息')
            getData()
        })
    }



function dateString(time) {
  var date = new Date(time);
  var year = date.getFullYear();
  var month = date.getMonth() + 1;
  var day = date.getDate();
  month = month < 10 ? "0" + month : month;
  day = day < 10 ? "0" + day : day;
  return year + "-" + month + "-" + day;
}
function toVideoDetail(id) {
    window.location.href = './video_detail.html?id='+id
}

