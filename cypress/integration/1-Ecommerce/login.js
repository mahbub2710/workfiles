import LoginPage from "../../PageObject/Ecommerce/login";

describe('DemoQA Website Automation', () => {
    const lp = new LoginPage ();

    it('Login Page Test', () => {
        lp.visit();
        lp.signIn();
    });

    it('', () => {
        
    });

});