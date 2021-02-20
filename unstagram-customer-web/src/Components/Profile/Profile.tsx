import React, { useState, useEffect } from 'react';
import ProfileBox from 'Styles/Profile/ProfileBox';
import ProfileButtons from 'Components/Profile/ProfileButtons';
import ProfileImage from 'Components/Profile/ProfileImage';
import ProfileFollow from 'Components/Profile/ProfileFollow';
import { checkWidthDevice } from 'Contexts/WindowSize';

const Profile: React.FC = () => {
    const isMe: boolean = false;
    const isFollowing: boolean = true;
    const isDesktop = checkWidthDevice();

    const Desktop: React.FC = ({ children }) => {
        return <React.Fragment>
            {isDesktop ? children : null}
        </React.Fragment>
    }
    
    const Mobile: React.FC = ({ children }) => {
        return <React.Fragment>
            {isDesktop ? null : children}
        </React.Fragment>
    }

    return (
        <ProfileBox>
            <section className="Profile-Wrapper">
                <ProfileImage/>
                <section className="Profile-About">
                    <ProfileButtons
                        isMe={isMe}
                        isFollowing={isFollowing}
                        />
                    <Desktop>
                        <ProfileFollow/>
                        <section className="Profile-Detail">
                            <h1 className="Profile-Detail-Name">이름</h1>
                            <span className="Profile-Detail-Explain">나의 설명~~</span>
                        </section>
                    </Desktop>
                </section>
            </section>

            <Mobile>
                <div className="Profile-Detail">
                    <h1 className="Profile-Detail-Name">이름</h1>
                    <span className="Profile-Detail-Explain">나의 설명~~</span>
                </div>
                <ProfileFollow/>
            </Mobile>
        </ProfileBox>
    );
}

export default Profile;