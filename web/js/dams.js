function drawDamPercentages(canvas, damNamesToPercentage, damsArray, keepAspect) {

    var ctx = canvas.getContext("2d");
    let height = canvas.height;
    let width = canvas.width;
    ctx.clearRect(0, 0, width, height);
    ctx.beginPath();

    // console.debug("damsArray: " + damsArray);
    let numOfDamsInArray = damsArray.length;
    let totalCapacity = 0;
    // compute total capacity and total percentage
    for (let i = 0; i < numOfDamsInArray; i++) {
        totalCapacity += damsArray[i].capacity;
    }
    // draw dams and compute total percentage
    let totalQuantity = 0;
    let x = 0;
    for (let i = 0; i < numOfDamsInArray; i++) {
        // console.log(i + " -> " + damsArray[i] + " ~ " + damsArray[i].nameEn);
        totalQuantity += damsArray[i].capacity * damNamesToPercentage.damNamesToPercentage[damsArray[i].nameEn];

        if(keepAspect)
            damWidth = Math.round((damsArray[i].capacity / totalCapacity) * width);
        else
            damWidth = Math.round((width - x) / (numOfDamsInArray - i));

        ctx.moveTo(x + damWidth, 0);
        ctx.lineTo(x + damWidth, height);
        ctx.stroke();
        let waterHeight = height * damNamesToPercentage.damNamesToPercentage[damsArray[i].nameEn];
        ctx.fillStyle="#b5f7ff";
        ctx.fillRect(x, height - waterHeight, damWidth, waterHeight);
        x = x + damWidth;

        let damPercentage = (100.0 * damNamesToPercentage.damNamesToPercentage[damsArray[i].nameEn]).toFixed(1);
        let damCapacity = (damsArray[i].capacity/1000000).toFixed(1);

        ctx.save();
        ctx.rotate(-Math.PI/2);
        ctx.font = "bold 9px verdana";
        ctx.fillStyle="#3333ff";
        ctx.fillText(damsArray[i].nameEn + " (" + damPercentage + "% of " + damCapacity + " MCM)", -height * 0.95, x-10);
        ctx.restore();
    }
    let totalPercentage = totalQuantity / totalCapacity;
    ctx.moveTo(0, height * (1-totalPercentage));
    ctx.strokeStyle = '#ff3333';
    ctx.lineTo(width, height * (1-totalPercentage));
    ctx.stroke();
    ctx.strokeStyle = '#000000';
}
