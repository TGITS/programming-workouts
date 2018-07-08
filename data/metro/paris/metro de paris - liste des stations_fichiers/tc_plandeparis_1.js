/*
 * tagContainer Generator v5
 * Copyright Tag Commander
 * http://www.tagcommander.com/
 * Generated: 2018-02-23 19:47:13 Europe/Paris
 * ---
 * Version	: 61.00
 * IDTC 	: 1
 * IDS		: 534
 */
/*!compressed by YUI*/ if(typeof tC=="undefined"){if(typeof document.domain=="undefined"||typeof document.referrer=="undefined"){document=window.document}(function(m,k){var j,r,y=m.document,a=m.location,e=m.navigator,x=m.tC,v=m.$,H=Array.prototype.push,b=Array.prototype.slice,u=Array.prototype.indexOf,g=Object.prototype.toString,i=Object.prototype.hasOwnProperty,o=String.prototype.trim,c=function(J,K){return new c.fn.init(J,K,j)},B=/[\-+]?(?:\d*\.|)\d+(?:[eE][\-+]?\d+|)/.source,q=/\S/,t=/\s+/,d=/^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g,w=/^(?:[^#<]*(<[\w\W]+>)[^>]*$|#([\w\-]*)$)/,l=/^<(\w+)\s*\/?>(?:<\/\1>|)$/,D=/^[\],:{}\s]*$/,z=/(?:^|:|,)(?:\s*\[)+/g,G=/\\(?:["\\\/bfnrt]|u[\da-fA-F]{4})/g,E=/"[^"\\\r\n]*"|true|false|null|-?(?:\d\d*\.|)\d+(?:[eE][\-+]?\d+|)/g,I=/^-ms-/,p=/-([\da-z])/gi,F=function(J,K){return(K+"").toUpperCase()},f={};c.fn=c.prototype={constructor:c,init:function(J,M,P){var L,N,K,O;if(!J){return this}if(J.nodeType){this.context=this[0]=J;this.length=1;return this}if(typeof J==="string"){if(J.charAt(0)==="<"&&J.charAt(J.length-1)===">"&&J.length>=3){L=[null,J,null]}else{L=w.exec(J)}if(L&&(L[1]||!M)){if(L[1]){M=M instanceof c?M[0]:M;O=(M&&M.nodeType?M.ownerDocument||M:y);J=c.parseHTML(L[1],O,true);if(l.test(L[1])&&c.isPlainObject(M)){this.attr.call(J,M,true)}return c.merge(this,J)}else{N=y.getElementById(L[2]);if(N&&N.parentNode){if(N.id!==L[2]){return P.find(J)}this.length=1;this[0]=N}this.context=y;this.selector=J;return this}}else{if(!M||M.tC){return(M||P).find(J)}else{return this.constructor(M).find(J)}}}else{if(c.isFunction(J)){return P.ready(J)}}if(J.selector!==k){this.selector=J.selector;this.context=J.context}return c.makeArray(J,this)},each:function(K,J){return c.each(this,K,J)},ready:function(J){c.ready.promise(J);return this}};c.fn.init.prototype=c.fn;c.extend=c.fn.extend=function(){var S,L,J,K,P,Q,O=arguments[0]||{},N=1,M=arguments.length,R=false;if(typeof O==="boolean"){R=O;O=arguments[1]||{};N=2}if(typeof O!=="object"&&!c.isFunction(O)){O={}}if(M===N){O=this;--N}for(;N<M;N++){if((S=arguments[N])!=null){for(L in S){J=O[L];K=S[L];if(O===K){continue}if(R&&K&&(c.isPlainObject(K)||(P=c.isArray(K)))){if(P){P=false;Q=J&&c.isArray(J)?J:[]}else{Q=J&&c.isPlainObject(J)?J:{}}O[L]=c.extend(R,Q,K)}else{if(K!==k){O[L]=K}}}}}return O};c.extend({ssl:(("https:"==y.location.protocol)?"https://manager.":"http://redirect534."),randOrd:function(){return(Math.round(Math.random())-0.5)},nodeNames:"abbr|article|aside|audio|bdi|canvas|data|datalist|details|figcaption|figure|footer|header|hgroup|mark|meter|nav|output|progress|section|summary|time|video",rnocache:/<(?:script|object|embed|option|style)/i,rnoshimcache:new RegExp("<(?:"+c.nodeNames+")[\\s/>]","i"),rchecked:/checked\s*(?:[^=]|=\s*.checked.)/i,containersLaunched:{}});c.extend({inArray:function(N,K,M){var J,L=Array.prototype.indexOf;if(K){if(L){return L.call(K,N,M)}J=K.length;M=M?M<0?Math.max(0,J+M):M:0;for(;M<J;M++){if(M in K&&K[M]===N){return M}}}return -1},isFunction:function(J){return c.type(J)==="function"},isArray:Array.isArray||function(J){return c.type(J)==="array"},isWindow:function(J){return J!=null&&J==J.window},isNumeric:function(J){return !isNaN(parseFloat(J))&&isFinite(J)},type:function(J){return J==null?String(J):f[g.call(J)]||"object"},each:function(O,P,L){var K,M=0,N=O.length,J=N===k||c.isFunction(O);if(L){if(J){for(K in O){if(P.apply(O[K],L)===false){break}}}else{for(;M<N;){if(P.apply(O[M++],L)===false){break}}}}else{if(J){for(K in O){if(P.call(O[K],K,O[K])===false){break}}}else{for(;M<N;){if(P.call(O[M],M,O[M++])===false){break}}}}return O},log:function(J,K){try{if(c.getCookie("tCdebugLib")&&console){console[K?K:"log"](J)}}catch(L){}}});c.each("Boolean Number String Function Array Date RegExp Object".split(" "),function(K,J){f["[object "+J+"]"]=J.toLowerCase()});j=c(y);var h={};function C(K){var J=h[K]={};c.each(K.split(t),function(M,L){J[L]=true});return J}c.buildFragment=function(M,N,K){var L,J,O,P=M[0];N=N||y;N=!N.nodeType&&N[0]||N;N=N.ownerDocument||N;if(M.length===1&&typeof P==="string"&&P.length<512&&N===y&&P.charAt(0)==="<"&&!c.rnocache.test(P)&&(c.support.checkClone||!c.rchecked.test(P))&&(c.support.html5Clone||!c.rnoshimcache.test(P))){J=true;L=jQuery.fragments[P];O=L!==k}if(!L){L=N.createDocumentFragment();c.clean(M,N,L,K);if(J){c.fragments[P]=O&&L}}return{fragment:L,cacheable:J}};var s=a.hostname,n=s.split("."),A="^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5]).){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$";if(n.length<2||s.match(A)){c.maindomain=s}else{c.maindomain=n[n.length-2]+"."+n[n.length-1]}m.tC=c})(window)}tC.extend({internalvars:typeof tC.internalvars!="undefined"?tC.internalvars:{},internalFunctions:typeof tC.internalFunctions!="undefined"?tC.internalFunctions:{},privacyVersion:"",containerVersion:"61.00",id_container:"1",id_site:"534",generatorVersion:"1.0.0",dedup_done:typeof tC.dedup_done!="undefined"?tC.dedup_done:false});tC.extend({launchTag:function(e,c,d,a,b){tC.array_launched_tags.push(c);tC.array_launched_tags_keys.push(e);tC.containersLaunched[a][b].t.push({id:e,label:c,idTpl:d});window.postMessage('TC.EX:{"id":"'+e+'","idc":"'+b+'","idt":"'+d+'","ids":"'+a+'","lb":"'+c.replace(/"/g,'\\"')+'"}',"*")}});if(tC.containersLaunched===undefined){tC.containersLaunched={}}if(tC.containersLaunched[534]===undefined){tC.containersLaunched[534]={}}tC.containersLaunched[534][1]={v:"61.00",t:[]};tC.extend({domReady:false,isDOMReady:function(){if(document.readyState=="complete"||document.readyState=="loaded"){return true}if(document.readyState!="interactive"){return false}if(!document.documentElement.doScroll){return true}try{document.documentElement.doScroll("left");return true}catch(a){return false}},waitingOnDomReadyCallBacks:tC.waitingOnDomReadyCallBacks||[],excuteOnDomReadyCallBacks:function(){for(var a=0;a<tC.waitingOnDomReadyCallBacks.length;a++){tC.waitingOnDomReadyCallBacks[a]()}tC.waitingOnDomReadyCallBacks=[]},onDomReady:function(b){if(this.domReady){b();return}tC.waitingOnDomReadyCallBacks.push(b);var a=false;if(document.addEventListener){a=true;document.addEventListener("DOMContentLoaded",function(){document.removeEventListener("DOMContentLoaded",arguments.callee,false);tC.excuteOnDomReadyCallBacks()},false)}else{if(document.attachEvent){a=true;document.attachEvent("onreadystatechange",function(){if(document.readyState==="complete"){document.detachEvent("onreadystatechange",arguments.callee);tC.excuteOnDomReadyCallBacks()}});if(document.documentElement.doScroll&&window==window.top){(function(){if(tC.domReady){return}try{document.documentElement.doScroll("left")}catch(c){setTimeout(arguments.callee,0);return}tC.excuteOnDomReadyCallBacks()})()}}}if(!a){window.onload=tC.excuteOnDomReadyCallBacks}}});if(tC.isDOMReady()){tC.domReady=true}else{tC.onDomReady(function(){tC.domReady=true})}tC.extend({isCurrentVersion:function(){var a=tC.getCookie("tc_mode_test"),b="testModeIncludeReplaceThisByTrue";return a!="1"||(a=="1"&&b=="true")}});tC.extend({pixelTrack:{add:function(a,b){a=a||0;b=b||"img";tC.onDomReady(function(){if(b=="iframe"){var c=document.createElement(b);c.src=a;c.width=1;c.height=1;c.style.display="none";document.body.appendChild(c)}else{var c=new Image();c.src=a}})}}});tC.extend({tc_hdoc:false,domain:function(){this.tc_hdoc=document;try{try{this.tc_hdoc=top.document}catch(d){this.tc_hdoc=document}var a=typeof this.tc_hdoc.domain!="undefined"?this.tc_hdoc.domain.toLowerCase().split("."):false,g="^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5]).){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$";if(a.length<2||this.tc_hdoc.domain.match(g)){return""}else{var f=a[a.length-3],c=a[a.length-2],b=a[a.length-1];if(c=="co"||c=="com"){return"."+f+"."+c+"."+b}else{return"."+c+"."+b}}}catch(d){tC.log(["tC.domain error : ",d],"warn")}}});tC.domain();tC.extend({removeCookie:function(a){this.setCookie(a,"",-1)},setCookie:function(c,e,a,h,d,g){if(!d){d=tC.domain()}var b=new Date();b.setTime(b.getTime());if(a){a=a*1000*60*60*24}var f=new Date(b.getTime()+(a));document.cookie=c+"="+escape(e)+((a)?";expires="+f.toGMTString():"")+((h)?";path="+h:";path=/")+((d)?";domain="+d:"")+((g)?";secure":"")},getCookie:function(a){return(result=new RegExp("(?:^|; )"+encodeURIComponent(a)+"=([^;]*)").exec(document.cookie))?unescape(result[1]):""}});tC.extend({storage:{has:function(){try{if("localStorage" in window&&window.localStorage!==null){window.localStorage.setItem("TC_CHECK","1");window.localStorage.removeItem("TC_CHECK");return true}return false}catch(a){return false}},get:function(a){return this.has()?window.localStorage.getItem(a):false},set:function(b,a){return this.has()?(window.localStorage.setItem(b,a)||true):false},remove:function(a){return this.has()?(window.localStorage.removeItem(a)||true):false}}});tC.extend({hitCounter:function(){if(Math.floor(Math.random()*parseInt(60))==0){tC.pixelTrack.add("//manager.tagcommander.com/utils/hit.php?id=1&site=534&version=61.00&frequency=60&position="+tC.container_position+"&rand="+Math.random())}}});tC.container_position=(typeof tc_container_position!=="undefined")?tc_container_position:(typeof tC.container_position!=="undefined")?tC.container_position:0;tC.container_position++;if(typeof tc_container_position!=="undefined"){tc_container_position++}tC.hitCounter();tC.extend({script:{add:function(d,f,c){var a=(document.getElementsByTagName("body")[0]||document.getElementsByTagName("script")[0].parentNode),b=document.createElement("script");b.type="text/javascript";b.async=true;b.src=d;b.charset="utf-8";if(a){if(f){if(b.addEventListener){b.addEventListener("load",function(){f()},false)}else{b.onreadystatechange=function(){if(b.readyState in {loaded:1,complete:1}){b.onreadystatechange=null;f()}}}}if(c&&typeof c=="number"){setTimeout(function(){if(a&&b.parentNode){a.removeChild(b)}},c)}a.insertBefore(b,a.firstChild)}else{tC.log("tC.script error : the element <script> or <body> is not found ! the file "+d+" is not implemented !","warn")}}}});tC.extend({_R:{cR:function(a){tC.storage.set("tC_Sync",a);tC.pixelTrack.add("//engage.commander1.com/reach?tc_s=534")},rR:function(){if(tC.storage.has()){var a=new Date().getTime();var b=tC.storage.get("tC_Sync")||0;b=parseInt(b);if(b==0||a-b>604800000){this.cR(a)}}}}});tC.onDomReady(function(){tC._R.rR()});tC.fn.css=function(b){try{this.each(function(g,j){for(var d in b){var h="";if(/-/.test(d)){var c=d.split("-");for(var g in c){if(g==0){h=c[g]}else{var f=c[g].split(""),k=f.shift();h+=k.toUpperCase()+f.join("")}}}else{var h=d}j.style[h]=b[d]}})}catch(a){tC.log(["tC.fn.css->error",a.message],"warn")}return this};tC.fn.resetCss=function(){this.each(function(a,b){tC(b).css({border:"none",background:"none",font:"none",margin:"none",padding:"none",top:"none",left:"none",buttom:"none",right:"none",width:"none",height:"none"})});return this};if(!tC.privacy){tC.extend({privacy:{reactived:null,id:null,version:null,categories:null,cookieData:null,init:function(){this.categories=tC.getCookie(this.getCN()+"_categories").split(",");this.cookieData=tC.getCookie(this.getCN()).split("@@@")},set:function(a){this.settings=a},getCN:function(){return typeof tc_privacy_cookie_name!="undefined"?tc_privacy_cookie_name:"TC_OPTOUT"},activTag:function(e,c){var d=e.split("@@@");if(d.length>2){var b=d[2].split("|");for(var a=0;a<b.length;a++){if(c==b[a]){return c}}return false}return c},In:function(c,a,b){b=b?b:"";this.cok(0,a,b);this.hit(1,a,c,b)},Out:function(c,a,b){b=b?b:"";this.cok(1,a,b);this.hit(0,a,c,b)},cok:function(b,c,d){var e=typeof tc_privacy_force_domain!=="undefined"?tc_privacy_force_domain:null;tC.setCookie(this.getCN(),b+"@@@"+c+"@@@"+d,396,"/",e)},hit:function(b,c,e,d){var f=tC.getCookie(tC.privacy.getCN()+"_categories");tC.pixelTrack.add(tC.ssl+"commander1.com/privacyHit.php?id_tc=1&site=534&version="+c+"&id_privacy="+e+"&privacy_action="+b+"&list_tag="+d+"&list_categories="+f+"&rand="+Math.random())},validRules:function(c){if(this.cookieData===null){this.init()}if(!this.cookieData.length||(this.cookieData.length===1&&this.cookieData[0]==="")){return true}var a=parseInt(this.cookieData[0]||0),b=this.cookieData[1]||0,d=(this.cookieData[2]||"").split("|");return(a===0&&(tC.inArray(c.toString(),d)!==-1||tC.inArray("ALL",d)!==-1))||(a===1&&(tC.inArray(c.toString(),d)===-1&&tC.inArray("ALL",d)===-1))},isEnable:function(){if(this.cookieData===null){this.init()}return(this.cookieData.length<=2||(this.reactivate!=""&&this.cookieData[1]==this.reactivate))},getContainer:function(a){return a.getElementById("tc_div_preview")||a.body},hitCounter:function(a){tC.pixelTrack.add("//manager.tagcommander.com/utils/privacyHit.php?id="+tC.id_container+"&site="+tC.id_site+"@&version="+tC.privacyVersion+"&id_privacy="+a+"&privacy_action=V&rand="+Math.random())}}})}tC534_1=tC;/* RETRO COMPATIBILITY FUNCTIONS */


if(typeof tc_vars=='undefined')var tc_vars=[];(function(){var l='url|user_email|segmentid|env_template|tc_data_segmentID'.split('|');for(var k in l){if(!tc_vars.hasOwnProperty(l[k])){tc_vars[l[k]]='';}}})();

/*DYNAMIC JS BLOCK 1*/


/*END DYNAMIC JS BLOCK 1*/

/*CUSTOM_JS_BLOCK1*/
tc_vars['env_template']=document.location.href;
/*END_CUSTOM_JS_BLOCK1*/
tC.array_launched_tags=[];tC.array_launched_tags_keys=[];tC.id_site='534';if(tC.getCookie('tc_mode_test')==1){(function(){var tc_testmodescriptload=document.createElement('script');tc_testmodescriptload.type='text/javascript';tc_testmodescriptload.src='//manager.tagcommander.com/utils/test_mode_include.php?id=1&site=534&type=load&rand='+Math.random()+'&version=';(document.getElementsByTagName('body')[0]||document.getElementsByTagName('head')[0]||document.getElementsByTagName('script')[0].parentNode).appendChild(tc_testmodescriptload);})();}else{
/*VARIABLES_BLOCK*/

var tc_array_url_vars=new Array();var temp_location=document.location.href.split('?').slice(1).join('?');if(temp_location.indexOf('#')>0){temp_location=temp_location.split('#').reverse().slice(1).reverse().join('#');}
temp_location=temp_location.replace(/%3d/g,'=');var temp_array=temp_location.split('&');for(var i=0;i<temp_array.length;i++){temp_array2=temp_array[i].split('=');tc_array_url_vars[temp_array2[0]]=temp_array2[1];}
var tc_fulldomain=window.location.hostname;var tc_maindomain='';var tmp1=window.location.hostname.split('.');if(tmp1[tmp1.length-2].length<=3){tc_maindomain=tmp1[tmp1.length-3]+'.'+tmp1[tmp1.length-2]+'.'+tmp1[tmp1.length-1];}else{tc_maindomain=tmp1[tmp1.length-2]+'.'+tmp1[tmp1.length-1];}
var tc_pathname=window.location.pathname;var tc_random_tmp=new String(Math.random());var tc_random=tc_random_tmp.substring(2,11);var tc_referrer=document.referrer;var tc_ssl=(("https:"==document.location.protocol)?"yes":"no");var tc_timestamp=Math.round(new Date().getTime()/1000.0);var tc_title=document.title;var tc_url=document.location.href;var tc_url_1_tmp=document.location.href.split('?');var tc_url_1_tmp2=tc_url_1_tmp[0].split('/');var tc_url_1=tc_url_1_tmp2[3];var tc_url_2_tmp=document.location.href.split('?');var tc_url_2_tmp2=tc_url_2_tmp[0].split('/');var tc_url_2=tc_url_2_tmp2[4];var tc_url_3_tmp=document.location.href.split('?');var tc_url_3_tmp2=tc_url_3_tmp[0].split('/');var tc_url_3=tc_url_3_tmp2[5];var temp_tc_url_no_query=document.location.href.split('?');var tc_url_no_query=temp_tc_url_no_query[0];var temp_tc_url_query_string=document.location.href.split('?');temp_tc_url_query_string.shift();tc_url_query_string=temp_tc_url_query_string.join('?');var url=window.location.pathname;var filename=url.substring(url.lastIndexOf('/')+1);tC.internalvars.filename=filename.substring(0,filename.lastIndexOf('.'));var userLang=navigator.language||navigator.userLanguage;tC.internalvars.userLang='en';if(userLang=='fr'){tC.internalvars.userLang='fr';}

/*END_VARIABLES_BLOCK*/


/*DYNAMIC JS BLOCK 2*/


/*END DYNAMIC JS BLOCK 2*/

/*CUSTOM_JS_BLOCK2*/

/*END_CUSTOM_JS_BLOCK2*/}

//----------------------------------------------------




//----

if(tC.getCookie('tc_mode_test')==1){(function(){var tc_testmodescriptexec=document.createElement('script');tc_testmodescriptexec.type='text/javascript';tc_testmodescriptexec.src='//manager.tagcommander.com/utils/test_mode_include.php?id=1&site=534&type=exec&rand='+Math.random()+'&version=61.00';(document.getElementsByTagName('body')[0]||document.getElementsByTagName('head')[0]||document.getElementsByTagName('script')[0].parentNode).appendChild(tc_testmodescriptexec);})();(function(){setTimeout(function(){if(typeof top.tc_count!=='undefined'){top.tc_count++;}else{top.tc_count=1;}var tc_newscript=document.createElement('script');tc_newscript.type='text/javascript';tc_newscript.src='//manager.tagcommander.com/utils/livetest/bookmarklet.php?r='+Math.random()+'&nb='+top.tc_count+'&container=534!1&version=61.00';(document.getElementsByTagName('body')[0]||document.getElementsByTagName('head')[0]||document.getElementsByTagName('script')[0].parentNode).appendChild(tc_newscript);},1000);})();}else{tC.launchTag('69','Commanders Act - DataCommander - v1.0','1729','534','1');var tCdata3=tCdata3||[];var tc_vars=tc_vars||[];tCdata3.push(['cs','534']);tCdata3.push(['id_call',Math.round(Math.random()*12345678942)]);tCdata3.push(['data',tc_vars]);tCdata3.push(['sendhit']);(function(){var tc_dms=document.createElement('script');tc_dms.type='text/javascript';tc_dms.async=true;tc_dms.src='//cdn.tagcommander.com/dms/engage.js';(document.getElementsByTagName('body')[0]||document.getElementsByTagName('script')[0].parentNode||document.getElementsByTagName('head')[0]).insertBefore(tc_dms,null);})();var tC_funcEngage=function(audiences){tC.setCookie("tCdebugLib",1)
tC.log(audiences);tC.setCookie('TCAUDIENCE',audiences.join('|||'));};setTimeout(function(){var scriptElt1=document.createElement("script");scriptElt1.id="tc_script__1";scriptElt1.src="https://api.commander1.com/api/dms/segmentation/segments?site=534&tcid=&callback=tC_funcEngage";(document.getElementsByTagName('body')[0]||document.getElementsByTagName('script')[0].parentNode).insertBefore(scriptElt1,null);},1000);tC.launchTag('70','Commanders Act - DataCommander - Retreive SegmentID V2','2077','534','1');var tmp_audiences_values=tmp_audiences_values||{};var cook_audiences=tC.getCookie('TCAUDIENCE');if(cook_audiences!==''){var tc_audiences=cook_audiences.split('|||');if(tc_audiences.length>0){for(i=0;i<tc_audiences.length;i++){tmp_audiences_values['audience_'+(i+1)]=tc_audiences[i];}
tc_vars["tc_data_segmentID"]=JSON.stringify(tmp_audiences_values);}}
tC.launchTag('71','Facebook Custom Audience','26','534','1');!function(f,b,e,v,n,t,s){if(f.fbq)return;n=f.fbq=function(){n.callMethod?n.callMethod.apply(n,arguments):n.queue.push(arguments)};if(!f._fbq)f._fbq=n;n.push=n;n.loaded=!0;n.version='2.0';n.queue=[];t=b.createElement(e);t.async=!0;t.src=v;s=b.getElementsByTagName(e)[0];s.parentNode.insertBefore(t,s)}(window,document,'script','https://connect.facebook.net/en_US/fbevents.js');fbq('init','1966791770215725');fbq('track','PageView');if(tC.privacy.validRules('52')){tC.launchTag('52','Measure Site Tracking Only 3.5','1905','534','1');if(typeof tC.msr!=="object"){tC.msr=[];}
tC.onDomReady(function(){tC.msr.dns='plandeparis.info.commander1.com';tC.msr.id_site='534';tC.msr.page_name=tc_pathname;tC.msr.page_type='';tC.msr.rand=Math.random();tC.msr.additional_params='';tC.msr.px=new Image();tC.msr.px.id="tc_img__1";tC.msr.src='';if(typeof tC.msr.page_name!=='undefined'&&tC.msr.page_name!=null&&tC.msr.page_name!=''){tC.msr.src+='&p='+tC.msr.page_name;}
if(typeof tC.msr.page_type!=='undefined'&&tC.msr.page_type!=null&&tC.msr.page_type!=''){tC.msr.src+='&pt='+tC.msr.page_type;}
if(typeof tC.msr.additional_params!=='undefined'&&tC.msr.additional_params!=null&&tC.msr.additional_params!=''){tC.msr.src+=tC.msr.additional_params;}
tC.msr.hdoc='';try{if(typeof top!='undefined'&&typeof top.document!='undefined'){tC.msr.hdoc=top.document;}}catch(e){}
if(tC.msr.hdoc===''){tC.msr.hdoc=document;};if(typeof tC.msr.hdoc.referrer!=='undefined'&&tC.msr.hdoc.referrer!=null&&tC.msr.hdoc.referrer!=''){if(tC.msr.hdoc.referrer.indexOf("?")!=-1){tC.msr.src+='&ref='+tC.msr.hdoc.referrer.substr(0,tC.msr.hdoc.referrer.indexOf("?"));}else{tC.msr.src+='&ref='+tC.msr.hdoc.referrer;}}
tC.msr.px.src='//'+tC.msr.dns+'/s3/?tcs='+tC.msr.id_site+'&rand='+tC.msr.rand+tC.msr.src;(document.getElementsByTagName('body')[0]||document.getElementsByTagName('head')[0]).appendChild(tC.msr.px);});}
if(tC.privacy.validRules('34')){tC.launchTag('34','Google Analytics (2011)','37','534','1');(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)})(window,document,'script','//www.google-analytics.com/analytics.js','ga');ga('create','UA-2803924-2',{'cookieDomain':'plandeparis.info','siteSpeedSampleRate':100});ga('send','pageview');}
if(tC.privacy.validRules('36')){tC.launchTag('36','Xiti','587','534','1');Xt_param='s=318715&p='+tc_pathname;try{Xt_r=top.document.referrer;}
catch(e){Xt_r=document.referrer;}
Xt_h=new Date();Xt_i='<img id="tc_img_36_1" width="39" height="25" border="0" alt="" ';Xt_i+='src="http://logv143.xiti.com/hit.xiti?'+Xt_param;Xt_i+='&hl='+Xt_h.getHours()+'x'+Xt_h.getMinutes()+'x'+Xt_h.getSeconds();if(parseFloat(navigator.appVersion)>=4)
{Xt_s=screen;Xt_i+='&r='+Xt_s.width+'x'+Xt_s.height+'x'+Xt_s.pixelDepth+'x'+Xt_s.colorDepth;}
document.write(Xt_i+'&ref='+Xt_r.replace(/[<>"]/g,'').replace(/&/g,'$')+'">');}
tC.launchTag('68','Google Adwords Tag','26','534','1');tC.template={};tC.template.img=document.createElement("img");tC.template.img.id="test_adwords";tC.template.img.type="text/javascript";tC.template.img.src="//googleads.g.doubleclick.net/pagead/viewthroughconversion/850508989/?guid=ON&amp;script=0";(document.getElementsByTagName('head')[0]||document.getElementsByTagName('body')[0]||document.getElementsByTagName('script')[0].parentNode).insertBefore(tC.template.img,null);}
var tc_privacy_used=0;var tc_privacy_display_1=function(){if(tC.isCurrentVersion()){tC.script.add('//cdn.tagcommander.com/privacy/534/privacy_1.js');}}
var tc_privacy_cpt=0;function tc_privacy_wait_body_1(){if(document.body!=null){tc_privacy_display_1();}else{tc_privacy_cpt++;if(tc_privacy_cpt<20){setTimeout("tc_privacy_wait_body_1()",100);}}}
tc_privacy_wait_body_1();