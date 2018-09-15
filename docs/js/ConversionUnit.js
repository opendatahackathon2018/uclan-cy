class ConversionUnit {

    constructor(id, nameEl, nameEn, imageURL, ratio) {
        this._id = id;
        this._nameEl = nameEl;
        this._nameEn = nameEn;
        this._imageURL = imageURL;
        this._ratio = ratio;
    }


    get id() {
        return this._id;
    }

    get nameEl() {
        return this._nameEl;
    }

    get nameEn() {
        return this._nameEn;
    }

    get imageURL() {
        return this._imageURL;
    }

    get ratio() {
        return this._ratio;
    }

}