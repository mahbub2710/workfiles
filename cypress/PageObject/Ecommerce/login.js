import Data from "../../support/utils"

class LoginPage{

    visit()
    {
        cy.visit('http://automationpractice.com/index.php');
    }

    signIn()
    {
        cy.get("a[title='Log in to your customer account']").click();
        cy.url().should('include', Data.singInUrlText);
    }



};

    export default LoginPage;