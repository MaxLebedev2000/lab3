function selectR(button, value) {
    let buttons = document.getElementsByClassName("rbl");
    for (let buton of buttons) {
        buton.style.backgroundColor = "#7FFF00";
    }
    button.style.backgroundColor = "#c2453d";
    document.getElementById("form:r").value = value;
    console.log("Value"+value);
    console.log("Value mas"+buttons.length);
    remoteFunction();
    redraw();

}

function selectX(button, value) {
    let buttons = document.getElementsByClassName("xbl");
    for (let buton of buttons) {
        buton.style.backgroundColor = "#7FFF00";
    }
    button.style.backgroundColor = "#c2453d";
    document.getElementById("form:x").value = value;
    console.log("Value"+value);
    console.log("Value mas"+buttons.length);

}

function test() {
    console.log("Test");
}



function listener(e) {

    let rect = canvas.getBoundingClientRect();
    let canx = (e.clientX - rect.left - LINE_WIDTH / 2);
    let cany = (e.clientY - rect.top - LINE_WIDTH / 2);

    let r = document.getElementById("form:r").value;

    let res = fromCanvas({"x": canx, "y": cany, "r": r});

    document.getElementById("canvas:r-value").value = r;
    document.getElementById("canvas:x-value").value = res.x;
    document.getElementById("canvas:y-value").value = res.y.toString();

    document.getElementById("canvas:canvasButton").click();

}

function onloadfunc() {
    let r = document.getElementById("form:r").value;
    let id;
    switch (r) {
        case "1":
            id = "one";
            break;
        case "1.5":
            id = "two";
            break;
        case "2":
            id = "three";
            break;
        case "2.5":
            id = "four";
            break;
        case "3":
            id = "five";
            break;
        default:
            id = "one";
            r = "1";
            break;
    }
    selectR(document.getElementById('form:' + id), r);
    selectX(document.getElementById('form:def' ), 0);
}

function toCanvas(point) {

    let x = point.x;
    let y = point.y;

    if (x > 0) {
        x = width / 2 + x * (width - 2 * padding) / 10;
    } else {
        x = width / 2 + x * (width - 2 * padding) / 10;
    }

    if (y > 0) {
        y = height / 2 - y * (height - 2 * padding) / 10;
    } else {
        y = height / 2 - y * (height - 2 * padding) / 10;
    }

    return {"x": x, "y": y, "r": point.r};
}

function fromCanvas(canPoint) {
    let x = canPoint.x;
    let y = height - canPoint.y;

    if (x < width / 2) {
        x = -(width / 2 - x);
    } else {
        x = x - width / 2;
    }

    if (y < height / 2) {
        y = -(height / 2 - y);
    } else {
        y = y - height / 2;
    }

    x = x / ((width - 2 * padding) / 10);
    y = y / ((height - 2 * padding) / 10);

    return {"x": x, "y": y, "r": canPoint.r};

}