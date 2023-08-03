const setting_menu_el = document.getElementById('setting-menu-id');
const setting_box_el = document.getElementById('setting-box-id');
const setmenu1_el = document.getElementById('setmenu1');
const setmenu1_out_el = document.getElementById('setmenu1-out');

setting_menu_el.addEventListener('mouseenter',function(){
    setting_box_el.style.display="block";
})
setmenu1_el.addEventListener('mouseenter',function(){
    setmenu1_out_el.style.display="block";
})


setting_menu_el.addEventListener('mouseleave',function(){setting_box_el.style.display="none",setmenu1_out_el.style.display="none"})
setting_box_el.addEventListener('mouseleave',function(){setting_box_el.style.display="none",setmenu1_out_el.style.display="none"})


