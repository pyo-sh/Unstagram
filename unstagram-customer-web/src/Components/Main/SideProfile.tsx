import React, { useState, useEffect } from 'react';
import SideProfileBox, { SideProfileWrapper } from 'Styles/Main/SideProfileBox';

import { debounce } from 'lodash';

const SideProfile: React.FC = () => {
    // SideProfile 의 위치가 고정이기 때문에
    // window size 가 변경, resize 가 될 때 마다 위치를 변경해주어야 한다
    // 반응형 화이팅~!!~!~!~!
    const [windowWidthSize, setWindowWidthSize] = useState<number>(1000);

    // resize 를 px 단위로 하면 resource 낭비가 아닐 수 없다.
    // lodash 의 debounce 를 이용해 ms 단위로 해당 이벤트를 감지
    const handleResize = debounce(() => {
        setWindowWidthSize(window.innerWidth);
    }, 25);
    
    useEffect(() => {
        // 초기 Rendering 시 오류 발생 고려 해주어야 한다. (useEffect 는 그 후에 실행되서 괜춘)
        setWindowWidthSize(window.innerWidth);
        window.addEventListener('resize', handleResize);
        return () => { // cleanup 
            window.removeEventListener('resize', handleResize);
        };
    }, []);
    
    return  windowWidthSize >= 1000
    ? (
        <SideProfileWrapper>
            <SideProfileBox
                windowWidth={windowWidthSize}
                >
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
        </SideProfileWrapper>
    )
    : null;
}

export default SideProfile;