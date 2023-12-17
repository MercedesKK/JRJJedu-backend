
var goodsName = decodeURI(window.location.href).split('goodsName=')[1]
console.log(goodsName,' goodsName goodsName goodsName goodsName goodsName')
layui.use(['layer'],function(){
    var layer = layui.layer;
})



elyasAjax('video/findByModal',JSON.stringify({name:goodsName})).then(res=>{
    let list = res.data.list;
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

function toVideoDetail(id) {
    window.location.href = './video_detail.html?id='+id
}
