package com.example.facebook

class feedProvider {
    companion object{
        val  feedList = listOf<Feed>(
            Feed("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRIWhE2ZtDlp5e6sbop1SNImYdCFSS53aSkIA","Mi querido amigo","TheAdmin1029","","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTIvf-oaj1Q4Xz6Nta8rju1kJt0lbPdr-hc8QlCe0S2mDDRhSTriXBlHIaX4rQDO6XHpO8","👍❤️ 20",2,0),
            Feed("https://i.pinimg.com/236x/13/71/c1/1371c163a3d785d5bb4bfd3e8c78b2fb.jpg","Siempe de chill","Daniel Vargas","","https://img.freepik.com/vector-gratis/meme-cuadrado-gato-vibrante-simple_742173-4493.jpg","👍😮 100",5,20),
            Feed("https://i.pinimg.com/236x/13/71/c1/1371c163a3d785d5bb4bfd3e8c78b2fb.jpg","jejex ","Andrea vargas","","https://img2.rtve.es/i/?w=1600&i=1620920852252.jpg","😂 50",0,0),
            Feed("https://s.yimg.com/ny/api/res/1.2/SGUs.R_MHuqdibgCw.1tLA--/YXBwaWQ9aGlnaGxhbmRlcjt3PTY0MDtoPTY0MA--/https://media.zenfs.com/en/homerun/feed_manager_auto_publish_494/47b53482de54ae42b9dc54faf8889145","","Ayerve","","https://www.techsmith.es/blog/wp-content/uploads/2023/08/hacer-meme-1024x923.png","😂❤️👍 40",30,2)

        )
        val notifyList  = listOf<Notify>(
            Notify("Evaluna te ha envió una solicitud de amistad.","https://vivolabs.es/wp-content/uploads/2022/03/perfil-mujer-vivo.png", "Hace 7 horas", true),
            Notify("Chayanne Compartió un enlace en tu biografía.","https://s.yimg.com/ny/api/res/1.2/SGUs.R_MHuqdibgCw.1tLA--/YXBwaWQ9aGlnaGxhbmRlcjt3PTY0MDtoPTY0MA--/https://media.zenfs.com/en/homerun/feed_manager_auto_publish_494/47b53482de54ae42b9dc54faf8889145", "21 feb a las 8:00 p. m.",true),
            Notify("Juan te ha envió una solicitud de amistad","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcScskPIMZJMMekOCpeHWnVtlhll8Fi73yCoYA", "21 feb a las 7:31 p. m.",false)

        )
    }
}