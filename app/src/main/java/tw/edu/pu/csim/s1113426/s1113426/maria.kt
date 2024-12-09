package tw.edu.pu.csim.s1113426.s1113426

class maria(screenH:Int, scale:Float) {
    var w = (100 * scale).toInt()  //小男孩寬度
    var h = (220 * scale).toInt()  //小男孩高度
    var x = 0  //小男孩x軸座標
    var y = screenH - h  //小男孩y軸座標

}
