// 获取Id
var id = location.search.replace(/[^\d]/g, "")
console.log(id,'article id @@')

layui.use(['layer'],function(){
    var layer = layui.layer;
})



elyasAjax('video/type/detail?id='+id).then(res=>{
    let list = res.data.videoList;
    let Item = ''

    list.forEach((item,index) => {
        Item += `<li class="video_item" onclick="toVideoDetail(${item.id})">
        <a href="javascript:;">
          <div class="img">
            <img src="${item.imgUrl}" alt="">
            <div class="toolbar">
              <div class="left">
                <span class="toolItem">
                  <i class="fas fa-play"></i>
                  ${item.playNum}
                </span><span class="toolItem">
                  <i class="far fa-comment-dots"></i>
                  ${item.momentCommentNum}
                </span>
              </div>

              <div class="right">
                <span class="toolItem">
                
                </span>
              </div>
            </div>
          </div>
          <span class="title">${item.name}</span>
          <span class="video_info">
            <p class="userName">${item.userName}</p>
            <p class="createdTimee">${dateString(item.createdAt)}</p>
          </span>
        </a>
      </li>`
    });
    $('.all_video').append(Item)
})

// 商品分类
   elyasAjax('video/type/findByModal',JSON.stringify({limit:999,page:1})).then(res =>{
    let list = res.data.list;
    let Item = ''

    list.forEach((item,index)=>{
        Item += `<li class="${item.id == id ? 'type_item typeactive': 'type_item'}" onclick="toTypes(${item.id})">${item.name}</li>`
    })

    $('.middle_types').html(Item);
})


function  toTypes(id) {
    window.location.href = './type.html?id='+id
}



