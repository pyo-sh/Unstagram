import React from 'react';
import SignInBox from 'Styles/Sign/SignInBox';

const SignIn: React.FC = () => {
  return (
    <SignInBox>
      <h1 className="SignIn-Title Unstagram-Title">Unstagram</h1>
      <form className="SignIn-Form">
        <input
          className="SignIn-Form-Input"
          placeholder="전화번호, 사용자 이름 또는 이메일"
          value=""/>
        <input 
          className="SignIn-Form-Input"
          placeholder="비밀번호"
          value=""/>
        <button className="SignIn-Form-Button">로그인</button>
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