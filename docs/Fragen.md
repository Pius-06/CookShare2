### Maven ###
- mvn clean	              Alte Build-Dateien löschen
- mvn compile	          Quellcode kompilieren
- mvn test	              Tests ausführen
- mvn package	          JAR/WAR erzeugen
- mvn install	          Artefakt lokal installieren (für andere Projekte)
- mvn dependency:tree     Zeigt alle Abhängigkeiten des Projekts

Definition: 
Maven ist ein Build- und Projektmanagement-Tool für Java, das den Build-Prozess, Abhängigkeiten, Plugins und die 
Projektstruktur standardisiert. 
Maven ist im Team wichtig, weil es sicherstellt, dass alle Entwickler mit denselben Abhängigkeiten und Versionen 
arbeiten und das Projekt auf allen Rechnern gleich gebaut und ausgeführt werden kann.

- Abhängigkeiten (Dependencies): Es sind fremde Bibliotheken die nicht im JDK enthalten sind, die dein Projekt benötigt, 
um zu funktionieren. Z.B.: import org.junit.Test;
    - JDK → liefert java.*
    - Maven → liefert org.*, com.*, net.*

- Build-Prozess: Der Build-Prozess ist die automatische Abfolge von Schritten, um aus Quellcode ein lauffähiges Programm 
zu machen.

- Plugins: Plugins erweitern Maven um konkrete Funktionen. Sie führen die einzelnen Schritte des Build-Prozesses aus, 
wie Kompilieren oder Testen.
    - maven-compiler-plugin → kompiliert Java
    - maven-surefire-plugin → führt Tests aus

- JAR: Eine JAR-Datei ist ein Archiv, das kompilierte Java-Klassen und Ressourcen enthält und als Bibliothek oder 
ausführbare Anwendung genutzt wird.
- Eigene Bibliotheken: Man kann eine eigene Bibliothek als JAR erstellen, diese mit Maven installieren oder in ein 
Repository hochladen und dann in anderen Projekten als Abhängigkeit einbinden und wie jede andere Library importieren.

-pom.xml: 


### Git ###
git log --oneline --graph:
- Zeigt den Verlauf des aktuellen Branches und inklusive aller Commits aus Branches, die in ihn gemergt wurden.
- mit --all werden alle Branches gezeigt auch die mit denen der BRanch nicht gemerget wurde.

q: 
- exit

HEAD:
- HEAD ist ein Zeiger auf den Commit, auf dem du gerade arbeitest.

.java → Compiler → .class → JVM → Programm läuft

Was steht in einer .class-Datei?
- Java Bytecode
- Methoden
- Felder
- Typinformationen
- Annotationen
- Metadaten für JVM & Frameworks
- ➡️ Die JVM führt .class-Dateien aus, nicht .java

Warum .class und nicht .java?
- Zur Laufzeit existieren keine .java-Dateien mehr.
- Spring sieht niemals .java, sondern nur .class-Dateien
- .java  = Bauplan (Papier)
- .class = echtes Bauteil (Maschine)

Was ist Person.class?
- Es ist ein Objekt vom Typ Class<Person>
- Class<Person> clazz = Person.class;

Wann verwendet man .class?
- Immer dann, wenn du die Klasse selbst meinst – nicht ein Objekt

# Neu:
- @Transactional // ???
- @JsonInclude(JsonInclude.Include.NON_NULL) // Null-Felder nicht anzeigen ???
- return recipes.stream()
                .map(recipe -> from(recipe))
                .collect(Collectors.toSet()); // ???
- // ???
    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return authorities;
    }
- private final UserService userService; // ??? Dependency Injection

- // ??? jakarta, lombok, jpa, hibernate, springboot

- @EnableScheduling // Aktiviert die ganze Scheduling-Infrastruktur und füht @Scheduled-Methoden aus

- @Pattern(regexp = """
            ((?=.*[A-Z]){1}
            (?=.*[0-9]){1}
            (?=.*[!@#$%^&*]){1}
            ).+$""", message = "Password must contain at least one upercase letter, one number, one special character")
    // ^: Präfix
    // $: Ende des Strings
    // ?=.*: Der String muss irgendwo das Pattern enthalten (?=PATTERN)
    // .* = beliebige Zeichen, 0 oder mehr

- @Configuration // Spring-Konfigurationsklasse
- @EnableWebSecurity // aktiviert Spring Security
- @RequiredArgsConstructor // erzeugt Konstruktor für final Felder
- @EnableMethodSecurity // erlaubt @PreAuthorize, @Secured usw. auf Methoden

- // liest JWT aus dem Header und authentifiziert den User
        private final JwtAuthenticationFilter jwtAuthFilter;
        // prüft Benutzer + Passwort / Token
        private final AuthenticationProvider authenticationProvider;
        // löscht Token / Session beim Logout
        private final LogoutService logoutService;

- // ist ein globaler Controller-Helfer. Er gilt für alle Controller an einer zentralen Stelle
@ControllerAdvice

- // Wenn irgendwo in einem Controller eine ApiException geworfen wird, dann rufe
    // diese Methode auf
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApiException(ApiException ex, WebRequest request) {...}

- ErrorResponse errorResponse = ErrorResponse.of(ex, request.getDescription(false));
        // .getDescription(false): liefert eine String-Beschreibung der aktuellen
        // HTTP-Anfrage.

- @Embeddable // Klasse bekommt keine eigene Tabelle sondern, sondern ihre Felder direkt in der Tabelle der verwendenden 
// Entität gespeichert werden.

- Was ist CustomUserDetails?

- :: verwendung

- @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .map(CustomUserDetails::new) // CustomUserDetails ist dein Wrapper für Spring Security
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
    }

- @Component // Spring erstellt automatisch eine Instanz dieses Filters.
@RequiredArgsConstructor
// OncePerRequestFilter: Filter läuft genau einmal pro Request (ideal für JWT).

- @RequiredArgsConstructor
public enum Permission {

    /*
     * .ADMIN_READ.name() => ADMIN_READ
     * ADMIN_READ.getPermission() => admin:read
     */
    ADMIN_READ("admin:read"), // singemäß: new Permission("admin:read");
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete");

    @Getter
    private final String permission;
}

- @OneToOne(cascade = CascadeType.ALL, // Operationen am Parent werden automatisch auf das Child angewendet
            orphanRemoval = true // Wenn die Beziehung entfernt wird, wird das Child gelöscht
    )

- Hibernate!!!

- private TestEntityManager entityManager; // Hilfsklasse zum Speichern/Flushen

- SecurityContextHolder.getContext().getAuthentication() == null

- if (jwtService.isTokenValid(jwt, userDetails)) {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails, // Wer ist eingelogt
                    null, // Passwort (nicht benötigt da JWT)
                    userDetails.getAuthorities()); // Was darf der User

            // Fügt technische Infos hinzu: IP-Adresse, Session-ID und Browser-Info
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // Ab diesem Moment weiß Spring: "Dieser Request gehört zu User X"
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

- config datein
- filter datein

- /*
         * SecurityConfig wird nur beim Start des servers ausgeführt!!!!!!!!!!!
         * 1. Request kommt bei Spring → Dispatcher.
         * 2. Spring Security Filter Chain läuft vor deinem Controller.
         * 3. SecurityFilterChain:
         * - Prüft CSRF, CORS, Session
         * - Prüft Authentifizierung via jwtAuthFilter etc.
         * - Prüft Authorization (requestMatchers + .hasRole() / .hasAuthority())
         * - Erlaubt Weiterleitung zum Controller oder
         * - wirft AccessDeniedException / AuthenticationException
         * 4. Controller wird nur erreicht, wenn alles passt.
         * => SecurityFilterChain ist wie Torwächter:
         * - entscheidet, wer rein darf, blockiert alles andere
         */
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(csrf -> csrf.disable())
                                .authorizeHttpRequests(req -> this.configureAuthorization(req))
                                // Keine HTTP Session
                                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                                // Spring Security sagt: „Wenn ich einen User authentifizieren muss, benutze
                                // dieses AuthenticationProvider-Objekt.“
                                .authenticationProvider(authenticationProvider)
                                // JWT wird vor Username/Password geprüft
                                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                                // User ausloggen
                                .logout(logout -> logout.logoutUrl("/auth/logout")
                                                // Token wird ungültig gemacht
                                                .addLogoutHandler(logoutService)
                                                .logoutSuccessHandler((req, res, auth) -> SecurityContextHolder
                                                                .clearContext()));

                return http.build();
        }

- @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(csrf -> csrf.disable())
                                .authorizeHttpRequests(req -> req.requestMatchers(WHITE_LIST_URL)
                                                .permitAll()
                                                .anyRequest()
                                                .authenticated())
                                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                                .authenticationProvider(authenticationProvider)
                                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                                .logout(logout -> logout.logoutUrl("/auth/logout")
                                                .addLogoutHandler(logoutService)
                                                .logoutSuccessHandler((req, res, auth) -> SecurityContextHolder
                                                                .clearContext()));

                return http.build();
        }

- protected Claims extractAllClaims(String jwt) {
        return Jwts
                // erzeugt ein Objekt, dass Jwt lesen, prüfen und extrahiern kann
                .parserBuilder()
                // Mit dem Secret Key soll überprüft werden, ob das Token echt ist
                .setSigningKey(getSignInKey())
                // Baut den fertigen Parser
                .build()
                // Prüft JWT
                .parseClaimsJws(jwt)
                // Holt den Payload-Teil = Claims
                .getBody();
    }