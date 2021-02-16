import React from 'react';
import SideProfileBox from 'Styles/Main/SideProfileBox';

const SideProfile: React.FC = () => {
    return (
        <SideProfileBox>
            <div className="SideProfile-User">
                <canvas className="SideProfile-User-Image">
                    <img/>
                </canvas>
                <div className="SideProfile-User-Detail">
                    <div className="SideProfile-User-Identification">아이디</div>
                    <div className="SideProfile-User-Name">이름</div>
                </div>
            </div>
            <div className="SideProfile-Footer">
                <div className="SideProfile-Explain">
                    아래 홈페이지를 따라 만들어 보는 공부 사이트입니다.
                </div>
                <a
                    className="SideProfile-Instagram"
                    href="https://www.instagram.com/"
                    >
                    © 2021 INSTAGRAM FROM FACEBOOK
                </a>
            </div>
        </SideProfileBox>
    )
}

export default SideProfile;