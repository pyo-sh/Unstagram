import { useEffect, useState } from 'react';

export const checkWidthDevice = () => {
    const [isDesktop, setIsDesktop] = useState<boolean>(true);

    useEffect(() => {
        const updateSize = () => {
            const isIt: boolean = window.innerWidth >= 736;
            setIsDesktop(isIt);
        };

        updateSize();

        window.addEventListener("resize", updateSize);

        return () => window.removeEventListener("resize", updateSize);
    }, []);

    return isDesktop;
};