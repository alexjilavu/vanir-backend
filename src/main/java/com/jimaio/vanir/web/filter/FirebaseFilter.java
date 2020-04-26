package com.jimaio.vanir.web.filter;

public class FirebaseFilter extends OncePerRequestFilter {

    private static String HEADER_NAME = "X-Authorization-Firebase";

    private FirebaseService firebaseService;

    public FirebaseFilter(FirebaseService firebaseService) {
        this.firebaseService = firebaseService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String xAuth = request.getHeader(HEADER_NAME);
        if (StringUtil.isBlank(xAuth)) {
            filterChain.doFilter(request, response);
            return;
        } else {
            try {
                FirebaseTokenHolder holder = firebaseService.parseToken(xAuth);

                String userName = holder.getUid();

                Authentication auth = new FirebaseAuthenticationToken(userName, holder);
                SecurityContextHolder.getContext().setAuthentication(auth);

                filterChain.doFilter(request, response);
            } catch (FirebaseTokenInvalidException e) {
                throw new SecurityException(e);
            }
        }
    }

}
