
layui.use(['layer'],function(){
    var layer = layui.layer;
})
// 根据token判断个人资料
let noToken = document.getElementById('noToken');
let yesToken = document.getElementById('yesToken');

console.log(token,"token");
if (!token) {
    yesToken.style.display = 'none'
} else if (token) {
    noToken.style.display = 'none'
}

// 分页
let page = 1; // 当前第几页
let limit = 100; // 一页显示多少条数据
let total = 0; //总共多少条
var elyasSize;

function prevPage(){
    if (page < 0 || page == 0) {
        return;
    }
    page--;
    getData();
}


function nextPage(){
    if (page > elyasSize) {
        return;
    }
    page++;
    getData();
}

function toPage(pageA){
    page = pageA
    getData()

}

// 获取商品数据

elyasAjax('video/findRankByModal').then(res =>{
    let list = res.data.list;
    let Item = ''
	console.log(res.data,"res.data")

    list.forEach((item,index)=>{
        if (index < 4) {
            Item += `<div class="slide" style="background-image: url('${item.imgUrl}');"></div>`
        }
       
    })

    $('#slides').append(Item);
})



getRankSix()
function getRankSix() {
    elyasAjax('video/findRankByModal').then(res =>{
    
        list = res.data.list;
        let Item = ''
     
        $('.video_random').html('');
        list.forEach((item,index)=>{
            if (index < 6) {
                Item += ` <li class="random_item" onclick="toVideoDetail(${item.id})">
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
                        
                        }
                        </span>
                      </div>
                    </div>
                  </div>
                  <span class="title">${item.name}</span>
                  <span class="video_info">
                    <p class="userName">${item.userName}</p>
                    <p class="createdTimee"${dateString(item.createdAt)}</p>
                  </span>
                </a>
              </li>`
            }
           
        })
    
        $('.video_random').append(Item);
    })
}


const reBtn = document.getElementById('resetBtn');
reBtn.addEventListener('click',()=>{
    getRankSix()
})




elyasAjax('video/findByModal').then(res =>{
    let list = res.data.list;
    let Item = ''
	console.log(res.data,"res.data")

    list.forEach((item,index)=>{
        Item += ` <li class="video_item" onclick="toVideoDetail(${item.id})">
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
       
    })

    $('.all_video').append(Item);
})

function toVideoDetail(id) {
    window.location.href = './video_detail.html?id='+id
}


elyasAjax('video/type/findByModal',JSON.stringify({limit:22,page:1})).then(res =>{
    let list = res.data.list;
    let Item = ''

    list.forEach((item,index)=>{
        if (index < 22) {
            Item += `<li class="type_item" onclick="toTypes(${item.id})">${item.name}</li>`
        }
    })

    $('.middle_types').append(Item);
})


function  toTypes(id) {
    window.location.href = './type.html?id='+id
}








function dateString(time){
    var date =new Date(time);
    var year = date.getFullYear();
    var month = date.getMonth()+1;
    var day = date.getDate();
    month = month < 10 ?"0"+month:month;
    day = day < 10 ?"0"+day:day;
    return month+"-"+day;
};




const slider = document.querySelector('.slider');
const slides = document.querySelector('.slides');
const prevBtn = document.querySelector('.prev');
const nextBtn = document.querySelector('.next');

let currentIndex = 0;
let interval;

function startAutoPlay() {
    interval = setInterval(() => {
        currentIndex++;
        if (currentIndex >= slider.children.length) {
            currentIndex = 0;
        }
        updateSlide();
    }, 3000);
}

function stopAutoPlay() {
    clearInterval(interval);
}

function updateSlide() {
    slides.style.transform = `translateX(-${currentIndex * 100}%)`;
    const activeIndex = currentIndex % slider.children.length;
    slider.querySelectorAll('.slide').forEach(slide => {
        slide.classList.remove('active');
    });
    slider.children[activeIndex].classList.add('active');
}

prevBtn.addEventListener('click', () => {
    stopAutoPlay();
    currentIndex--;
    if (currentIndex < 0) {
        currentIndex = slider.children.length - 1;
    }
    updateSlide();
    startAutoPlay();
});

nextBtn.addEventListener('click', () => {
    stopAutoPlay();
    currentIndex++;
    if (currentIndex >= slider.children.length) {
        currentIndex = 0;
    }
    updateSlide();
    startAutoPlay();
});

startAutoPlay();



