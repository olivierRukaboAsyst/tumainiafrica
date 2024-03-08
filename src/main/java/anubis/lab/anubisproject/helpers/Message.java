package anubis.lab.anubisproject.helpers;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Message {
    @Autowired
    private MessageSource messageSource;

    public Message(MessageSource messageSource) {
        super();
        this.messageSource = messageSource;
    }

    public String success(Locale locale) {
        String msg = messageSource.getMessage("message.success", null, locale);

        return msg;
    }

    public String avanceEncours(Locale locale) {
        String msg = messageSource.getMessage("message.avanceEncours", null, locale);

        return msg;
    }

    public String echec(Locale locale) {
        String msg = messageSource.getMessage("message.opEchou", null, locale);

        return msg;
    }

    public String existe(Locale locale) {
        String msg = messageSource.getMessage("message.exist", null, locale);

        return msg;
    }

    public String notExiste(Locale locale) {
        String msg = messageSource.getMessage("message.notExist", null, locale);

        return msg;
    }

    public String codeExist(Locale locale) {
        String msg = messageSource.getMessage("message.codeExist", null, locale);

        return msg;
    }

    public String policyNo(Locale locale) {
        String msg = messageSource.getMessage("message.policyNo", null, locale);

        return msg;
    }

    public String nameNotExist(Locale locale) {
        String msg = messageSource.getMessage("message.nameNotExist", null, locale);

        return msg;
    }

    public String msgNotExist(Locale locale) {
        String msg = messageSource.getMessage("message.msgNotExist", null, locale);

        return msg;
    }

    public String banknotExiste(Locale locale) {
        String msg = messageSource.getMessage("message.bankNotExist", null, locale);

        return msg;
    }

    public String vehiculeNotExist(Locale locale) {
        String msg = messageSource.getMessage("message.vehiNotExist", null, locale);

        return msg;
    }

    public String isContributionValide(Locale locale) {
        String msg = messageSource.getMessage("message.dejaValideContri", null, locale);

        return msg;
    }

    public String aucunElementTrouve(Locale locale) {
        String msg = messageSource.getMessage("message.zeroElement", null, locale);

        return msg;
    }

    public String isValide(Locale locale) {
        String msg = messageSource.getMessage("message.dejaValide", null, locale);

        return msg;
    }

    public String isValideReject(Locale locale) {
        String msg = messageSource.getMessage("message.valideContriReject", null, locale);

        return msg;
    }

    public String isNotValide(Locale locale) {
        String msg = messageSource.getMessage("message.notValide", null, locale);

        return msg;
    }

    public String isReject(Locale locale) {
        String msg = messageSource.getMessage("message.elementRejet", null, locale);

        return msg;
    }

    public String deleteConstraint(Locale locale) {
        String msg = messageSource.getMessage("message.deleteError", null, locale);

        return msg;
    }

    public String mailEnvoye(Locale locale) {
        String msg = messageSource.getMessage("message.send", null, locale);

        return msg;
    }

    public String mailEchoue(Locale locale) {
        String msg = messageSource.getMessage("message.sendError", null, locale);

        return msg;
    }

    public String dateControl(Locale locale) {
        String msg = messageSource.getMessage("message.dateError", null, locale);

        return msg;
    }

    public String datePieceError(Locale locale) {
        String msg = messageSource.getMessage("message.datePieceError", null, locale);

        return msg;
    }

    public String amountError(Locale locale) {
        String msg = messageSource.getMessage("message.amountError", null, locale);

        return msg;
    }

    public String savefileError(Locale locale) {
        String msg = messageSource.getMessage("message.savefileError", null, locale);

        return msg;
    }

    public String deleteFileError(Locale locale) {
        String msg = messageSource.getMessage("message.deleteFileError", null, locale);

        return msg;
    }

    public String ControlCloturation(Locale locale) {
        String msg = messageSource.getMessage("message.previousMsg", null, locale);

        return msg;
    }

    public String intervalIncorrect(Locale locale) {
        String msg = messageSource.getMessage("message.intervalError", null, locale);

        return msg;
    }

    public String balanceInsuffisant(Locale locale) {
        String msg = messageSource.getMessage("message.BalanceError", null, locale);

        return msg;
    }

    public String contributionNonTraite(Locale locale) {
        String msg = messageSource.getMessage("message.notvalideContriReject", null, locale);

        return msg;
    }

    public String compteClientBloque(Locale locale) {
        String msg = messageSource.getMessage("message.blocked", null, locale);

        return msg;
    }

    public String pourcentagePartenaire(Locale locale) {
        String msg = messageSource.getMessage("message.problemPartner", null, locale);

        return msg;
    }

    public String fileError(Locale locale) {

        String msg = messageSource.getMessage("message.fileError", null, locale);

        return msg;
    }

    public String affectationError(Locale locale) {

        String msg = messageSource.getMessage("message.affectationError", null, locale);
        return msg;
    }

    public String emailError(Locale locale) {

        String msg = messageSource.getMessage("message.emailError", null, locale);
        return msg;
    }

    public String passwordChanged(Locale locale) {

        String msg = messageSource.getMessage("message.passwordChanged", null, locale);
        return msg;
    }

    public String usernameIncorrect(Locale locale) {

        String msg = messageSource.getMessage("message.usernameIncorrect", null, locale);
        return msg;
    }

    public String usernameDesactive(Locale locale) {

        String msg = messageSource.getMessage("message.usernameDesactive", null, locale);
        return msg;
    }

    public String passwordIncorrect(Locale locale) {

        String msg = messageSource.getMessage("message.passwordIncorrect", null, locale);
        return msg;
    }

    public String utilisateurDejaAffecte(Locale locale) {

        String msg = messageSource.getMessage("message.utilisateurDejaAffecte", null, locale);
        return msg;
    }

    public String utilisateurNonAffecte(Locale locale) {

        String msg = messageSource.getMessage("message.utilisateurNonAffecte", null, locale);
        return msg;
    }

    public String utilisateurSujet(Locale locale) {

        String msg = messageSource.getMessage("message.utilisateurSujet", null, locale);
        return msg;
    }

    public String bonjour(Locale locale) {

        String msg = messageSource.getMessage("message.bonjour", null, locale);
        return msg;
    }

    public String info(Locale locale) {

        String msg = messageSource.getMessage("message.info", null, locale);
        return msg;
    }

    public String password(Locale locale) {

        String msg = messageSource.getMessage("message.password", null, locale);
        return msg;
    }

    public String modifyInfo(Locale locale) {

        String msg = messageSource.getMessage("message.modifyInfo", null, locale);
        return msg;
    }

    public String emailExist(Locale locale) {

        String msg = messageSource.getMessage("message.emailExist", null, locale);
        return msg;
    }

    public String cardExist(Locale locale) {

        String msg = messageSource.getMessage("message.cardExist", null, locale);
        return msg;
    }

    public String phoneExist(Locale locale) {

        String msg = messageSource.getMessage("message.phoneExist", null, locale);
        return msg;
    }

    public String nifExist(Locale locale) {

        String msg = messageSource.getMessage("message.nifExist", null, locale);
        return msg;
    }

    public String codepostalExist(Locale locale) {

        String msg = messageSource.getMessage("message.codepostalExist", null, locale);
        return msg;
    }

    public String plaqueExist(Locale locale) {

        String msg = messageSource.getMessage("message.plaqueExist", null, locale);
        return msg;
    }

    public String chassisExist(Locale locale) {

        String msg = messageSource.getMessage("message.chassisExist", null, locale);
        return msg;
    }

    public String usernameExist(Locale locale) {

        String msg = messageSource.getMessage("message.usernameExist", null, locale);
        return msg;
    }

    public String transferAttente(Locale locale) {
        String msg = messageSource.getMessage("message.transferAttente", null, locale);

        return msg;
    }

    public String contentValueError(Locale locale) {
        String msg = messageSource.getMessage("message.contentValueError", null, locale);

        return msg;
    }

    public String dayLessOrHight(Locale locale) {
        String msg = messageSource.getMessage("message.dayLessOrHight", null, locale);

        return msg;
    }

    public String pricingNotExist(Locale locale) {
        String msg = messageSource.getMessage("message.pricingNotExist", null, locale);
        return msg;
    }

    public String suspendedYet(Locale locale) {
        String msg = messageSource.getMessage("message.suspendedYet", null, locale);

        return msg;
    }
}
