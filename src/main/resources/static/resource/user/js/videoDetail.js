var id = location.search.replace(/[^\d]/g, "")
console.log(id,'article id @@')

layui.use(['layer'],function(){
    var layer = layui.layer;
})

getDetail(id)
var detailInfo = {}
function getDetail(id) {
    elyasAjax('video/detail?id='+id).then(res =>{

        detailInfo = res.data;
        $('#name').text(res.data.name)
        $('#times').text(GetTile(res.data.createdAt))
    
        $('.videowindow').html(`<video src="${res.data.videoUrl}" controls></video>`)
    
        $('.contentt').html(res.data.graphicDetails)
        $('.playnumber').html(res.data.playNum)
        $('#comNum').text(res.data.momentCommentNum)
        $('.comNum').text(res.data.momentCommentNum)

        $('.user-info').html(`<img src="${res.data.avatar}" alt="" />
        <div class="btn-info">
          <div class="user">${res.data.userName}</div>
          <div class="bts">
            <a href="./siggleChat.html?id=${res.data.createdBy}">
            <button class="chat" type="button" id="toChat">消息</button>
</a>
            <button class="save">关注</button>
          </div>
        </div>`)

        let noLikeBtn = `<span class="btn-item" style="margin-right:20px" onclick="dianzan()">
                <i class="fas fa-thumbs-up" style="margin-right: 20px;"></i>
                <p>${res.data.likeNum}</p>
              </span>`
        let yesLikeBtn = ` <span class="btn-item" style="margin-right:20px" onclick="quxiaodianzan()">
                <i class="fas fa-thumbs-up" style="margin-right: 20px;color: #cf3145"></i>
                <p>${res.data.likeNum}</p>
              </span>`
        if (res.data.likeState == 1) {
            $('.btnwrapper').html(yesLikeBtn)
        }else {
            $('.btnwrapper').html(noLikeBtn)
        }

        let comItem = ''
        res.data.momentCommentList.forEach(item =>{
            comItem += `<div class="comment-item">
                <img
                  src="${item.avatar}
                            "
                  alt=""
                />
                <div class="comment-box">
                  <h4 class="userName">${item.userName}</h4>
                  <p class="comment">
                    ${item.comment}
                  </p>
                  <div class="bottom-info">
                    <span class="comment-time">${GetTile(item.createdAt)}</span>

                    <span class="comment-little-btn" style="margin-left: 20px;" onclick="sendChilld(${item.id})">回复</span>
                  </div>
                  <div class="little-comment-list">`

            if (item.childrenList) {
                item.childrenList.forEach(child=>{
                    comItem += `<div class="little-comment">
                      <img
                        src="${child.avatar}
                                        "
                        alt=""
                      />
                      <h4 class="little-comment-userName">${child.userName}</h4>
                      <p class="little-comment-comment">
                       ${child.comment}
                      </p>
                      <span class="little-comment-time">${GetTile(child.createdAt)}</span>
                    </div>`
                })
            }


                comItem += `</div>
                </div>
              </div>`

        })

        $('.comment-list').html(comItem)






    })
}



elyasAjax('video/findRankByModal').then(res =>{
    let list = res.data.list;
    let html = '';

    list.forEach(element => {
        html += `  <div class="video-item"  onclick="toVideoDetail(${element.id})">
        <img src="${element.imgUrl}" alt="" />
        <div class="left-info">
          <div class="title">
           ${element.name}
          </div>
          <div class="up">
            <span class="upIcon">UP</span>
            <p>${element.userName}</p>
          </div>
          <div class="icons">
            <span class="icons-item">
              <i class="fas fa-play"></i>
              <p>${element.playNum}</p>
            </span>
            <span class="icons-item">
              <i class="far fa-comment-dots"></i>
              <p>${element.momentCommentNum}</p>
            </span>
          </div>
        </div>
      </div>`
    });

    $('.video-list').append(html)
})

let commentBtn = document.querySelector('#commentBtn')
commentBtn.addEventListener('click',()=>{
  let params = {
    momentId:id,
    comment:$('#comment_input').val()
  }

  elyasAjax('moment/comment/add',JSON.stringify(params)).then(res=>{
    if (res.code !== 200) {
        layer.msg(res.message);
        return;
    }else {
        layer.msg('评论成功');
        $('#comment_input').val('');
        getDetail(id)
    }
  })
})


function dianzan() {
    let params = {
        subjectId:id,
        type:1,
        status:1,
      }
    
      elyasAjax('moment/like/addOrCancel',JSON.stringify(params)).then(res=>{
        if (res.code !== 200) {
            layer.msg(res.message);
            return;
        }else {
           
            getDetail(id)
        }
      })
}
function quxiaodianzan() {
    let params = {
        subjectId:id,
        type:1,
        status:2,
    }

    elyasAjax('moment/like/addOrCancel',JSON.stringify(params)).then(res=>{
        if (res.code !== 200) {
            layer.msg(res.message);
            return;
        }else {

            getDetail(id)
        }
    })
}

function shoucang() {
    let params = {
        subjectId:id,
        type:1,
        state:1,
      }
    
      elyasAjax('moment/like/addOrCancel',JSON.stringify(params)).then(res=>{
        if (res.code !== 200) {
            layer.msg(res.message);
            return;
        }else {
            layer.msg('收藏成功');
            getDetail(id)
        }
      })
}

var parentId = ''

function sendChilld(id) {
    parentId = id
    document.getElementById("modal").style.display = "block";
}



document.getElementById("confirm").addEventListener("click", function() {
    let params = {
        momentId:id,
        comment:$('#commentInputz').val(),
        parentId:parentId
    }

    elyasAjax('moment/comment/add',JSON.stringify(params)).then(res =>{
        if (res.code !== 200) {
            layer.msg(res.message);
            return;
        }else {
            getDetail(id)
            document.getElementById("modal").style.display = "none";
        }
    })

});

document.getElementById("cancel").addEventListener("click", function() {

    document.getElementById("modal").style.display = "none";
});

function toVideoDetail(id) {
    window.location.href = './video_detail.html?id='+id
}



function GetTile(time) {
    var now = new Date(time)
    var nian = now.getFullYear()
    var yue = (now.getMonth() + 1).toString().padStart(2, '0')
    var ri = now.getDate().toString().padStart(2, '0')
    var shi = now.getHours().toString().padStart(2, '0')
    var fen = now.getMinutes().toString().padStart(2, '0')
    var miao = now.getSeconds().toString().padStart(2, '0')
    return `${nian}-${yue}-${ri} ${shi}:${fen}:${miao}`
}




