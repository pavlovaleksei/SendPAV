package Sourses;

import javax.mail.PasswordAuthentication;

/**
 * Класс аутентификации
 * @author Pavlov
 */
public class EmailAuthenticator extends javax.mail.Authenticator
{
	private final String login   ;
	private final String password;
        
        /**
         * Конструктор класса
         * @param login     Принимает логин пользователя
         * @param password  Принимает пароль пользователя
         */
	public EmailAuthenticator (final String login, final String password)
	{
		this.login    = login;
		this.password = password;
	}
        
        @Override
        /**
         * Метод получения логина/пароля
         */
	public PasswordAuthentication getPasswordAuthentication()
	{
		return new PasswordAuthentication(login, password);
	}
}
