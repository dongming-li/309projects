/**
 * @module colors
 */

const blue100 = '#AAD5FF',
    blue200 = '#7DBEFF',
    blue300 = '#51A8FF',
    blue400 = '#2793FF',
    blue500 = '#0080FF',
    blue600 = '#0072E4',
    blue700 = '#0267CC',
    blue800 = '#0458AD',
    blue900 = '#034990',
    neutral100 = '#FFFFFF',
    neutral200 = '#F6F6F6',
    neutral300 = '#EDEDED',
    neutral700 = '#7E7E7E',
    neutral800 = '#545454',
    neutral900 = '#2A2A2A',
    red = '#FF0800',
    yellow = '#FDE10E',
    green = '#5AAC44';

/**
 * Gets the hex values of the colors for our app
 */
export default class Colors {
    /**
     * Gets the hex value of the specified shade of the specified color
     * @param  {string} color
     * @param  {number} shade
     * @return {string} hex value of the specified shade of the specified color
     * @static
     */
    static getColor(color, shade) {
        if (color === 'blue') {
            this.getBlue(shade);
        } else if (color === 'neutral') {
            this.getNeutral(shade);
        } else if (color === 'red') {
            this.getRed();
        } else if (color === 'yellow') {
            this.getYellow();
        } else if (color === 'green') {
            this.getGreen();
        } else {
            console.log('[ERROR] No color specified');
        }
    }
    /**
     * Gets the hex value of the specified shade of blue
     * @param  {number} shade
     * @return {string} hex value of the specified shade of blue
     * @static
     */
    static getBlue(shade) {
        switch (shade) {
            case 100:
                return blue100;
            case 200:
                return blue200;
            case 300:
                return blue300;
            case 400:
                return blue400;
            case 500:
                return blue500;
            case 600:
                return blue600;
            case 700:
                return blue700;
            case 800:
                return blue800;
            case 900:
                return blue900;
            default:
                console.log('[WARN] Blue shade not specified. Defaulting to blue-500');
                return blue500;
        }
    }
    /**
     * Gets the hex value of the specified shade of neutral color
     * @param  {number} shade
     * @return {string} hex value of the specified shade of neutral color
     * @static
     */
    static getNeutral(shade) {
        switch (shade) {
            case 100:
                return neutral100;
            case 200:
                return neutral200;
            case 300:
                return neutral300;
            case 700:
                return neutral700;
            case 800:
                return neutral800;
            case 900:
                return neutral900;
            default:
                console.log('[ERROR] Invalid neutral shade specified');
        }
    }
    /**
     * Gets the hex value of red
     * @return {string} hex value of red
     * @static
     */
    static getRed() {
        return red;
    }
    /**
     * Gets the hex value of yellow
     * @return {string} hex value of yellow
     * @static
     */
    static getYellow() {
        return yellow;
    }

    /**
     * Gets the hex value of green
     * @return {string} hex value of green
     * @static
     */
    static getGreen() {
        return green;
    }
}
