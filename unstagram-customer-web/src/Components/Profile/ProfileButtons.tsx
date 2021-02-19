import React from 'react';
import ProfileButtonsBox, { buttonIcon } from 'Styles/Profile/ProfileButtonsBox';

const ProfileButtons: React.FC = () => {
    const isMe: boolean = false;
    const isFollowing: boolean = true;

    return (
        <ProfileButtonsBox>
            <h2 className="ProfileButtons-Identification">
                아이디이다
            </h2>
            <section className="ProfileButtons-Functions">
                {isMe
                ? <button className="ProfileButtons-Functions-Button ProfileButtons-Functions-Default">
                    프로필 편집
                </button>
                : isFollowing 
                    ? <> 
                        <button className="ProfileButtons-Functions-Button ProfileButtons-Functions-Default">
                            메세지 보내기
                        </button>
                        <button className="ProfileButtons-Functions-Button ProfileButtons-Functions-Button-Following">
                            <div>
                                <img className="ProfileButtons-Functions-Image" src="following.png"/>✓
                            </div>
                        </button>
                        <button className="ProfileButtons-Functions-Button ProfileButtons-Functions-Button-Menu">
                            <div className="ProfileButtons-Functions-Menu"></div>
                        </button>
                    </>
                    : <>
                        <button className="ProfileButtons-Functions-Button ProfileButtons-Functions-Button-Follow">
                            팔로우
                        </button>
                        <button className="ProfileButtons-Functions-Button ProfileButtons-Functions-Button-Menu2">
                            <div className="ProfileButtons-Functions-Menu2"></div>
                        </button>
                    </>
                }
            </section>
            <button className="ProfileButtons-Setting">
                {buttonIcon({
                    isMe: isMe,
                    className: "",
                    fill: "#262626",
                    width: 24,
                    height: 24,
                    viewBox: "0 0 48 48",
                })}
            </button>
        </ProfileButtonsBox>
    );
}

export default ProfileButtons;