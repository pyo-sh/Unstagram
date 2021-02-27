import React, { useState } from 'react';
import SignInBox from 'Styles/Sign/SignInBox';
import { useUserDispatch, loginDispatcher } from 'Contexts/UserContext';

const SignIn: React.FC = () => {
  const [userId, setUserId] = useState<string>('');
  const [password, setPassword] = useState<string>('');
  const userDispatch = useUserDispatch();

  const onChangeUserId = (event: React.ChangeEvent<HTMLInputElement>) => {
    setUserId(event.target.value);
  }

  const onChangePassword = (event: React.ChangeEvent<HTMLInputElement>) => {
    setPassword(event.target.value);
  }

  const onSubmitLogin = (event: React.FormEvent<HTMLButtonElement>) => {
    event.preventDefault();

    loginDispatcher(userDispatch, {
      userId,
      password
    });
  }

  return (
    <SignInBox>
      <h1 className="SignIn-Title Unstagram-Title">Unstagram</h1>
      <form className="SignIn-Form">
        <input
          onChange={onChangeUserId}
          className="SignIn-Form-Input"
          placeholder="전화번호, 사용자 이름 또는 이메일"
          value={userId}/>
        <input 
          onChange={onChangePassword}
          className="SignIn-Form-Input"
          placeholder="비밀번호"
          value={password}/>
        <button
          className="SignIn-Form-Button"
          onClick={onSubmitLogin}
          >
          로그인
        </button>
      </form>
      <div className="SignIn-Seperate">
        <div className="SignIn-Seperate-Line"></div>
        <div className="SignIn-Seperate-Title">또는</div>
        <div className="SignIn-Seperate-Line"></div>
      </div>
      <a className="SignIn-Password-Reset">비밀번호를 잊으셨나요?</a>
    </SignInBox>
  );
}

export default SignIn;